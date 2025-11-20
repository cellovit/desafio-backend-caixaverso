package br.gov.caixa.resources;

import br.gov.caixa.dto.request.SimularInvestimentoRequestDto;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestSecurity(authorizationEnabled = false)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class SimulacaoInvestimentoResourceTest {

    @Test
    @Order(1)
    public void testSimularInvestimento() {
        SimularInvestimentoRequestDto request = new SimularInvestimentoRequestDto(
                1L,
                BigDecimal.valueOf(1000),
                12,
                "CDB"
        );

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/simular-investimento")
                .then()
                .statusCode(200)
                .body("data", not(empty()))
                .body("data[0].resultadoSimulacaoDto.valorFinal", notNullValue());
    }

    @Order(2)
    @Test
    public void testGetHistoricoSimulacoes() {
        given()
                .queryParam("page", 1)
                .queryParam("pageSize", 5)
                .when()
                .get("/simulacoes")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Order(3)
    @Test
    public void testGetHistoricoSimulacoesPorProdutoDia() {
        given()
                .queryParam("page", 1)
                .queryParam("pageSize", 5)
                .when()
                .get("/simulacoes/por-produto-dia")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }


}

