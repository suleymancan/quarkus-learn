package org.promotion;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/promotions")
public class PromotionResource {

    private final PromotionService promotionService;

    public PromotionResource(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @POST
    public Response save(Promotion promotion){
        promotionService.save(promotion);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Promotion promotion){
        promotionService.update(id, promotion);
        return Response.ok().build();
    }
}
