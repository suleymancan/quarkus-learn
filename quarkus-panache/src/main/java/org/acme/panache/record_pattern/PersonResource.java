package org.acme.panache.record_pattern;

import org.acme.panache.LoggingFilter;
import org.acme.panache.PageableDto;
import org.jboss.logging.Logger;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/persons")
public class PersonResource {
    private static final Logger LOGGER = Logger.getLogger(PersonResource.class);

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("age") Integer age) {
        if (age != null) {
            return Response.ok(personService.getByAge(age)).build();
        }
        return Response.ok(personService.getAll()).build();
    }

    @GET
    @Path("/pageable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pageable(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            final PageableDto<Person> pageable = personService.getPageable(page, pageSize);
            LOGGER.infof("getPageable() invocation returning successfully");
            return Response.ok(pageable.getResource())
                    .header("count", pageable.getCount())
                    .header("totalPage", pageable.getTotalPage()).build();
        } catch (RuntimeException e) {
            final String message = e.getClass().getSimpleName() + ": " + e.getMessage();
            LOGGER.errorf("getPageable() invocation failed: %s", message);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(personService.getById(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid Person person) throws URISyntaxException {
        final Long id = personService.save(person);
        return Response.created(new URI("persons/" + id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Person person) {
        personService.update(id, person);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        personService.delete(id);
        return Response.noContent().build();
    }
}
