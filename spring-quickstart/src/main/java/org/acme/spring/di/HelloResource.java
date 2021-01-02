package org.acme.spring.di;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloResource {

    private final HelloConfig helloConfig;

    public HelloResource(HelloConfig helloConfig) {
        this.helloConfig = helloConfig;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(){
        return helloConfig.getHello();
    }
}
