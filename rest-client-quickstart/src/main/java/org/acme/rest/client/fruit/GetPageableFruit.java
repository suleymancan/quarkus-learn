package org.acme.rest.client.fruit;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;

public class GetPageableFruit {

    @QueryParam("page")
    private final int page;

    @QueryParam("pageSize")
    private final int pageSize;

    @HeaderParam("auth")
    private final String auth;

    public GetPageableFruit(int page, int pageSize, String auth) {
        this.page = page;
        this.pageSize = pageSize;
        this.auth = auth;
    }
}
