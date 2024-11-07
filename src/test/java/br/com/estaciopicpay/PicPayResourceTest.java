package br.com.estaciopicpay;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PicPayResourceTest {

    @Test
    public void testGetUsuarios() {

        given()
                .when().get("/api/usuarios")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCreateUsuario() {
        // Teste para verificar a criação de um novo usuário
        String newUserJson = "{\"nome\":\"João\",\"email\":\"joao@example.com\",\"tipo\":\"USUARIO_COMUM\"}";

        given()
                .contentType(ContentType.JSON)
                .body(newUserJson)
                .when().post("/api/usuarios")
                .then()
                .statusCode(201) // Verifica se o status da resposta é 201 Created
                .body("nome", equalTo("João"))
                .body("email", equalTo("joao@example.com"));
    }

    @Test
    public void testCreateLojista() {
        // Teste para verificar a criação de um novo lojista
        String newLojistaJson = "{\"nome\":\"Lojista1\",\"email\":\"lojista1@example.com\"}";

        given()
                .contentType(ContentType.JSON)
                .body(newLojistaJson)
                .when().post("/api/lojistas")
                .then()
                .statusCode(201) // Verifica se o status da resposta é 201 Created
                .body("nome", equalTo("Lojista1"))
                .body("email", equalTo("lojista1@example.com"));
    }
}
