package org.acme.rest.client.fruit;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("fruits")
public class FruitResource {

    @Inject
    @RestClient
    private FruitService fruitService;

    @GET
    @Path("/pageable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pageable(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        final GetPageableFruit getPageableFruit = new GetPageableFruit(page, pageSize, "auth");
        final Response response = fruitService.getPageableFruit(getPageableFruit);
        final List<FruitRequest> list = response.readEntity(List.class);
        return Response.ok(list)
                .header("count", response.getHeaderString("count"))
                .header("totalPage", response.getHeaderString("totalPage"))
                .build();
    }


    @POST
    public Response save(FruitRequest fruitRequest) {
        fruitService.save(new PostFruit("auth"), fruitRequest);
        return Response.ok().build();
    }
}
