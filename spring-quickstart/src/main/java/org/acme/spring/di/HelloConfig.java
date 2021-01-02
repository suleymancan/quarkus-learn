package org.acme.spring.di;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@Configuration
@ConfigurationProperties("greeting")
public class HelloConfig {

    //@Value("${greeting.hello}")
    private String hello;

    public String getHello() {
        return hello;
    }

    public void setHello(String value) {
        this.hello = value;
    }
}
