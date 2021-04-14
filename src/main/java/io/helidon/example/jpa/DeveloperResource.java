
package io.helidon.example.jpa;

import java.util.Collections;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.ok;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;
import javax.ws.rs.PathParam;


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

    @PersistenceContext 
    private EntityManager em;

    @Inject
    public DeveloperResource(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevelopers() {
        return ok(this.developerService.getAllDevelopers()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDeveloper(Developer developer) {
        return ok(this.developerService.createDeveloper(developer)).build();
    }

    @GET
    @Path("/{devId}")
    @Transactional 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeveloper(@PathParam("devId") String devId) {
        Developer developer = this.developerService.getDeveloper(devId);
        return ok(developer).build();
    }

    @DELETE
    @Path("/{devId}")
    @Transactional 
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeveloper(@PathParam("devId") String devId) {
        this.developerService.deleteDeveloper(devId);
        return ok().build();
    }

}
