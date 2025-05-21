package io.zutun.samples.it.steps;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @Then("the response code should be {int}")
    public void theStatusCodeShouldBe(Integer statusCode) {
        apiClient
                .getResponse()
                .then()
                .statusCode(statusCode);
    }

    @Then("the field {string} of the response should be {string}")
    public void bodyResponsePropertyIsEqualTo(String path, String value) {
        apiClient
                .getResponse()
                .then()
                .body(path, equalTo(value));
    }

    @When("the application health is verified")
    public void verifyApplicationHealth() {
        apiClient.performRequest(GET, "/actuator/health");
    }

    @Given("the following headers")
    public void withHeaders(DataTable headers) {
        headers.asMap(String.class, String.class).forEach(apiClient::withHeader);
    }

    @When("a new pet is registered")
    public void registerNewPet(JsonNode jsonPayload) {
        apiClient.withJsonPayload(jsonPayload.toString());
        apiClient.performRequest(POST, "/v1/pets");
    }

    @And("the response should contain a field {string} with UUID format")
    public void fielShouldHaveFormatUUID(String field) {
        apiClient.getResponse()
                .then()
                .body(field, matchesPattern("^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$"));
    }

    @When("a pet is searched by ID {string}")
    public void searchPetById(String id) {
        apiClient.performRequest(GET, "/v1/pets/" + id);
    }

}
