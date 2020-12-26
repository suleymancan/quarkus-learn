package org.acme.getting.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

//resource: https://quarkus.io/guides/config
@Path("/configs")
public class TestConfigResource {

    private final TestConfig testConfig;

    public TestConfigResource(TestConfig testConfig) {
        this.testConfig = testConfig;
    }

    @ConfigProperty(name = "greeting.message")
    public String message;


    @GET
    public Response getConfig(){
        return Response.ok(message).build();
    }

    @GET
    @Path("/class")
    public Response getClassConfig(){
        return Response.ok(testConfig).build();
    }
}
