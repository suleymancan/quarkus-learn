package org.acme.elasticsearch;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@ApplicationScoped
@Path("/promotions")
public class PromotionResource {

    private final PromotionService promotionService;

    public PromotionResource(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GET
    public Response get(@QueryParam("name") String name) throws IOException {
        return Response.ok(promotionService.searchByName(name)).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) throws IOException {
        return Response.ok(promotionService.getById(id)).build();
    }

    @POST
    public Response index(Promotion promotion) throws IOException {
        promotionService.index(promotion);
        return Response.ok().build();
    }
}
