package io.helidon.example.jpa;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        var errorResponse = new ErrorResponse();
        int status = 500;
        if( e instanceof EntityNotFoundException || e instanceof NotFoundException)
        {
            errorResponse.setErrorType(ErrorResponse.ErrorType.NOT_FOUND);
            status = 404;
        }
        else if (e instanceof BadRequestException || e instanceof ValidationException)
        {
            errorResponse.setErrorType(ErrorResponse.ErrorType.BAD_REQUEST);
            status = 400;
        }
        else {
            errorResponse.setErrorType(ErrorResponse.ErrorType.INTERNAL_SERVER_ERROR);
        }
        errorResponse.setMessage(e.getMessage());
        return Response.status(status).entity(errorResponse).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
