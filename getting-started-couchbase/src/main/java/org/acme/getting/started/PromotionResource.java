package org.acme.getting.started;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/promotions")
public class PromotionResource {

    private final PromotionRepository promotionRepository;

    public PromotionResource(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @POST
    public Response savePromotion(Promotion promotion){
        promotionRepository.insertPromotion(promotion);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromotionById(@PathParam("id") Long id){
        final Promotion promotion = promotionRepository.getPromotion(id);
        return Response.ok(promotion).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        promotionRepository.deletePromotion(id);
        return Response.noContent().build();
    }
}
