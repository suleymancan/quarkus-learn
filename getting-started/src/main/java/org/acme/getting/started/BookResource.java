package org.acme.getting.started;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Path("/languages")
public class BookResource {

    private static final Map<Integer, String> LANGUAGES = new HashMap<>();

    static {
        LANGUAGES.put(1, "Java");
        LANGUAGES.put(2, "Go");
        LANGUAGES.put(3, "C#");
        LANGUAGES.put(4, "JavaScript");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanguages(){
        return Response.ok(new ArrayList<>(LANGUAGES.values())).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanguages(@PathParam("id") Integer id){
        return Response.ok(LANGUAGES.get(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLanguages(Language language){
        LANGUAGES.put(language.getId(), language.getName());
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLanguages(@PathParam("id") Integer id, Language language){
        LANGUAGES.put(language.getId(), language.getName());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteLanguages(@PathParam("id") Integer id){
        LANGUAGES.remove(id);
        return Response.noContent().build();
    }

}
