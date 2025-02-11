package io.zutun.samples.it.steps;

import io.cucumber.spring.ScenarioScope;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.config.EncoderConfig.encoderConfig;

@Component
@ScenarioScope
public class ApiClient {

    private RequestSpecification request = initRequest();
    private final Map<String, Object> pathVariables = new HashMap<>();

    @Getter
    private Response response;

    private RequestSpecification initRequest() {
        return RestAssured
                .given()
                .config(getConfig())
                .baseUri("http://localhost:8080")
                .log()
                .ifValidationFails(LogDetail.ALL);
    }

    private static RestAssuredConfig getConfig() {
        return RestAssured
                .config()
                .encoderConfig(encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT));
    }

    public void withHeader(String key, String value) {
        request.header(key, value);
    }

    public void performRequest(Method method, String endpoint) {
        response = request
                .pathParams(pathVariables)
                .when()
                .request(method, endpoint);
    }

    public void withPathVariable(String key, String value) {
        this.pathVariables.put(key, value);
    }

    public void withQueryParams(Map<String, Object> queryParams) {
        request.queryParams(queryParams);
    }

    public void withQueryParam(String key, Object value) {
        request.queryParam(key, value);
    }

    public void withJsonPayload(String jsonPayload) {
        request
                .contentType(ContentType.JSON)
                .body(jsonPayload);
    }

}
