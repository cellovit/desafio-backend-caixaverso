package br.gov.caixa.resources;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.quarkus.test.h2.H2DatabaseTestResource;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestSecurity(authorizationEnabled = false)
public class InvestidorResourceTest {

    @Test
    public void testGetPerfilRiscoPorInvestidor() {
        given()
                .pathParam("clienteId", 1L)
                .when()
                .get("/perfil-risco/{clienteId}")
                .then()
                .statusCode(200)
                .body("clienteId", equalTo(1))
                .body("perfil", notNullValue())
                .body("pontuacao", notNullValue());
    }

    @Test
    public void testGetInvestimentosPorInvestidor() {
        given()
                .pathParam("clienteId", 1L)
                .when()
                .get("/investimentos/{clienteId}")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].id", notNullValue());
    }

    @Test
    public void testGetProdutosRecomendadosPorPerfil() {
        given()
                .pathParam("perfil", "MODERADO")
                .queryParam("page", 1)
                .queryParam("pageSize", 5)
                .when()
                .get("/produtos-recomendados/{perfil}")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].id", notNullValue());
    }
}
