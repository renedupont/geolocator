package org.renedupont.geolocator;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GeolocatorResourceTest {

    @Test
    public void testEndpoint_NotExistingInDb() {
        given()
          .when().get("/geolocation?host=127.0.0.1")
          .then()
             .statusCode(500)
             .body(is("The address 127.0.0.1 is not in the database."));
    }

}