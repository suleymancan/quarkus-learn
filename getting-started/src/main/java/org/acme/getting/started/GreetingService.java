package org.acme.getting.started;

import javax.enterprise.context.ApplicationScoped;

/**
 *

 Quarkus heavily utilizes Jandex at build time, to discover various classes or annotations. One immediately recognizable application of this, is CDI bean discovery. As a result, most of the Quarkus extensions will not work properly if this build time discovery isn’t properly setup.

 */
@ApplicationScoped
public class GreetingService {

    public String greeting(String name){
        return "hello " + name;
    }
}
