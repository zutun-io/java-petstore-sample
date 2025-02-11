package io.zutun.samples.it.steps;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.http.Method.GET;
import static org.hamcrest.Matchers.equalTo;

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

}
