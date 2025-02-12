package io.zutun.samples.it.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DocStringType;
import lombok.SneakyThrows;

public class ApiDefinitions {

    ObjectMapper jsonMapper = new ObjectMapper();

    @SneakyThrows
    @DocStringType
    public JsonNode jsonPayload(String jsonValue) {
        return jsonMapper.readTree(jsonValue);
    }

}
