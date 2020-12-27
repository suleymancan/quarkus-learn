package org.acme.rest.client.country;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Set;
import java.util.concurrent.CompletionStage;

// resource: https://quarkus.io/guides/rest-client

@Singleton
@Path("/v2")
@RegisterRestClient(configKey = "country-api")
public interface CountriesService {

    @GET
    @Path("/name/{name}")
    Set<Country> getByName(@PathParam("name") String name);


    @GET
    @Path("/name/{name}")
    CompletionStage<Set<Country>> getByNameAsync(@PathParam("name") String name);
}
