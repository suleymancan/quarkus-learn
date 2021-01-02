package org.acme.spring.di;

import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    private final HelloConfig helloConfig;

    public HelloController(HelloConfig helloConfig) {
        this.helloConfig = helloConfig;
    }


}
