package org.acme.rest.client.country;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/countries")
public class CountriesResource {

    @Inject
    @RestClient
    CountriesService countriesService;

    @GET
    @Path("{name}")
    public Set<Country> getName(@PathParam("name") String name) {
        return countriesService.getByName(name);
    }


    @GET
    @Path("/async/{name}")
    public CompletionStage<Set<Country>> nameAsync(@PathParam("name") String name) {
        return countriesService.getByNameAsync(name);
    }
}
