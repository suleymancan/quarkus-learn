package org.acme.getting.started;

import org.jboss.resteasy.annotations.Body;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * create:
 * mvn io.quarkus:quarkus-maven-plugin:1.10.5.Final:create \
 *     -DprojectGroupId=org.acme \
 *     -DprojectArtifactId=getting-started \
 *     -DclassName="org.acme.getting.started.GreetingResource" \
 *     -Dpath="/hello"
 *
 *

 With Quarkus, there is no need to create an Application class. It’s supported, but not required. In addition, only one instance of the resource is created and not one per request. You can configure this using the different *Scoped annotations (ApplicationScoped, RequestScoped, etc).

 ./mvnw compile quarkus:dev
 */
@Path("/hello")
public class GreetingResource {
    //Dependency injection in Quarkus is based on ArC which is a CDI-based dependency injection solution tailored for Quarkus' architecture.
    @Inject
    GreetingService greetingService;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
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