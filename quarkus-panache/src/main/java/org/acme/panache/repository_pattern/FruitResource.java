package org.acme.panache.repository_pattern;

import org.acme.panache.PageableDto;
import org.acme.panache.record_pattern.Person;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/fruits")
public class FruitResource {

    private final FruitRepository fruitRepository;

    public FruitResource(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("season") String season) {
        if (season != null) {
            return Response.ok(fruitRepository.getBySeason(season)).build();
        }
        return Response.ok(fruitRepository.listAll()).build();
    }

    @GET
    @Path("/pageable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pageable(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        final PageableDto<Fruit> pageable = fruitRepository.getPageable(page, pageSize);
        return Response.ok(pageable.getResource())
                .header("count", pageable.getCount())
                .header("totalPage", pageable.getTotalPage()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(fruitRepository.findById(id)).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Fruit fruit) throws URISyntaxException {
        fruitRepository.persist(fruit);
        return Response.created(new URI("fruits/" + fruit.getId())).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Fruit fruit) {
        fruitRepository.update(fruit, id);
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        fruitRepository.deleteById(id);
        return Response.noContent().build();
    }
}
