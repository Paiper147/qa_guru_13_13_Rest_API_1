package home.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class HomeReqresTests {
    private final static String URL = "https://reqres.in/";

    @Test
    void checkIdTheSecondUser() {
        given()
                .log().uri()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2));
    }

    @Test
    void check404WrongUser() {
        given()
                .log().uri()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void checkSuccessfulCreate(){
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .log().uri()
                .log().body()
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(URL + "api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void checkSuccessfulRegister(){
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        given()
                .log().uri()
                .log().body()
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(URL + "api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void checkSuccessfulUserUpdate(){
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
        String theNameBeforeChange = given()
                .log().uri()
                .log().body()
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .get(URL + "api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .path("data.first_name");

        given()
                .log().uri()
                .log().body()
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(URL + "api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"));
    }

}
