package io.zutun.samples.it.steps;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

@CucumberContextConfiguration
@SpringBootTest(classes = {ApiClient.class})
public class ApiSteps {

    @Autowired
    private ApiClient apiClient;

    @Entonces("El código de respuesta debe ser {int}")
    public void theStatusCodeShouldBe(Integer statusCode) {
        apiClient
                .getResponse()
                .then()
                .statusCode(statusCode);
    }

    @Entonces("El campo {string} de la respuesta debe ser {string}")
    public void bodyResponsePropertyIsEqualTo(String path, String value) {
        apiClient
                .getResponse()
                .then()
                .body(path, equalTo(value));
    }

    @Cuando("Se verifica la salud de la aplicación")
    public void verifyApplicationHealth() {
        apiClient.performRequest(GET, "/actuator/health");
    }

    @Dado("que se tienen los siguientes headers")
    public void withHeaders(DataTable headers) {
        headers.asMap(String.class, String.class).forEach(apiClient::withHeader);
    }

    @Cuando("Se registra una nueva mascota")
    public void registerNewPet(JsonNode jsonPayload) {
        apiClient.withJsonPayload(jsonPayload.toString());
        apiClient.performRequest(POST, "/v1/pets");
    }

    @Y("El response debe contener un campo {string} con formato UUID")
    public void fielShouldHaveFormatUUID(String field) {
        apiClient.getResponse()
                .then()
                .body(field, matchesPattern("^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$"));
    }
}
