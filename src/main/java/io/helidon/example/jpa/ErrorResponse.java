package io.helidon.example.jpa;

public class ErrorResponse {
    private ErrorType errorType;
    private String message;

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum ErrorType{
        INTERNAL_SERVER_ERROR,
        BAD_REQUEST,
        NOT_FOUND
    }
}
