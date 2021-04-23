
package io.helidon.example.jpa;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;


/**
 * A simple JAX-RS resource to greet you. Examples:
 *
 * Get default greeting message:
 * curl -X GET http://localhost:8090/greet
 *
 * The message is returned as a JSON object.
 */
@Path("/developers")
@RequestScoped
public class DeveloperResource {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    private final DeveloperService developerService;
    private final String DEVELOPERS_API_PATH = "/developers";

    @Inject
    public DeveloperResource(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDevelopers(@QueryParam("name")String name,
                                     @QueryParam("team")String team,
                                     @DefaultValue("1") @QueryParam("page")Integer page,
                                     @DefaultValue("10") @QueryParam("pageSize")Integer pageSize,
                                     @QueryParam("sort")String sort) {
        return ok(developerService.searchDevelopers(name,team,page,pageSize,sort)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDeveloper(Developer developer) {
        var createdDeveloper = this.developerService.createDeveloper(developer);
        return Response.status(Response.Status.CREATED).header("Location", DEVELOPERS_API_PATH + "/" + createdDeveloper.getId()).entity(developer).build();
    }

    @GET
    @Path("/{devId}")
    @Transactional 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeveloper(@PathParam("devId") String devId) {
        Developer developer = this.developerService.getDeveloper(devId);
        if(developer==null)
        {
            throw new NotFoundException();
        }
        return ok(developer).build();
    }

    @DELETE
    @Path("/{devId}")
    @Transactional 
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeveloper(@PathParam("devId") String devId) {
        this.developerService.deleteDeveloper(devId);
        return noContent().build();
    }

}
