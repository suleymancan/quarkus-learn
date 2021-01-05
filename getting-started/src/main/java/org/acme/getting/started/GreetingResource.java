package org.acme.getting.started;

import org.jboss.resteasy.annotations.Body;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService greetingService;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public String helloPathParam(@PathParam("name") String name) {
        return greetingService.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/query-param")
    public String helloQueryParam(@QueryParam("name") String name) {
        return greetingService.greeting(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Greeting postHello(Greeting body){
        return body;
    }
}