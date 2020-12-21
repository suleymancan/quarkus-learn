package org.acme.getting.started;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

// QuarkusTest: you instruct JUnit to start the application before the tests.
// These tests use RestAssured, but feel free to use your favorite library.
// ./mvnw test
// By default, tests will run on port 8081 so as not to conflict with the running application.


@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello"));
    }

    @Test
    public void testGreetingEndpoint(){
        final String greeting = "Suleyman";
        given()
                .pathParam("name", greeting)
                .when().get("/hello/{name}")
                .then()
                .statusCode(200)
                .body(is("hello " + greeting));
    }

}