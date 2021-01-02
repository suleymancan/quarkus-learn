package org.acme.spring.web;


import org.acme.spring.di.HelloConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final HelloConfig helloConfig;

    public GreetingController(HelloConfig helloConfig) {
        this.helloConfig = helloConfig;
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok(helloConfig.getHello());
    }
}
