package org.acme.rest.client.fruit;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/fruits")
@RegisterRestClient(configKey = "fruit-api")
//@ClientHeaderParam(name="auth", value="auth")
public interface FruitService {

    @GET
    @Path("/pageable")
    @Retry(maxRetries = 4)
    Response getPageableFruit(@BeanParam GetPageableFruit getPageableFruit);

    @POST
    void save(@BeanParam PostFruit postFruit, FruitRequest fruitRequest);
}
