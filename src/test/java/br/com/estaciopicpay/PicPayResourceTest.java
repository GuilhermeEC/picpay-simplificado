package br.com.estaciopicpay;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class PicPayResourceTest {

    @Test
    public void testCreateUser() {
        User user = new User("John Doe", "john.doe@example.com");

        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", is("John Doe"))
                .body("email", is("john.doe@example.com"));
    }

    @Test
    public void testFindUserById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("name", CoreMatchers.notNullValue())
                .body("email", CoreMatchers.notNullValue());
    }

    @Test
    public void testListAllUsers() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThan(0));
    }

    @Test
    public void testUpdateUser() {
        User updatedUser = new User("Jane Doe", "jane.doe@example.com");

        given()
                .pathParam("id", 1)
                .contentType("application/json")
                .body(updatedUser)
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(200)
                .body("name", is("Jane Doe"))
                .body("email", is("jane.doe@example.com"));
    }

    @Test
    public void testDeleteUser() {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(204);
    }
}
