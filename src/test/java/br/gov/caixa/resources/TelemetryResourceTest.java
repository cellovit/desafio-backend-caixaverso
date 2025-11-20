package br.gov.caixa.resources;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestSecurity(authorizationEnabled = false)
public class TelemetryResourceTest {

    @Test
    public void testGetTelemetryData() {
        given()
                .queryParam("dataInicio", "2025-01-01")
                .queryParam("dataFim", "2025-01-31")
                .when()
                .get("/telemetria") // ajuste o path conforme o mapeamento real
                .then()
                .statusCode(200)
                .body("servicos", notNullValue()); // valida que há dados retornados
    }
}
