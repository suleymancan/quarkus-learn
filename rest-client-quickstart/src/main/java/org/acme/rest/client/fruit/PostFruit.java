package org.acme.rest.client.fruit;

import javax.ws.rs.HeaderParam;

public class PostFruit {
    @HeaderParam("auth")
    private final String authorization;

    public PostFruit(String authorization) {
        this.authorization = authorization;
    }
}
