package qa.guru.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {
    /*
        1. make POST- request to https://reqres.in/api/login
            with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
        2. get response { "token": "QpwL5tke4Pnpja7X4" }
        3. check token is "QpwL5tke4Pnpja7X4"
     */

    @Test
    void loginTest() {
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
    @Test
    void negative415LoginTest(){
        given()
                .log().uri()
                .log().body()
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }
    @Test
    void negative400LoginTestMissingEmailUsername() {
        String requestBody = "123";
        given()
                .log().uri()
                .log().body()
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
    @Test
    void negative400LoginTestMissingPassword() {
        String bodyResponse = "{ \"email\": \"peter@klaven\" }";
        given()
                .log().uri()
                .log().body()
                .contentType(ContentType.JSON)
                .body(bodyResponse)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
