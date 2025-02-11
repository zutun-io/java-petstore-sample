package io.zutun.samples.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.zutun.samples.domain.Cat;
import io.zutun.samples.domain.Dog;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Tag(name = "Pets", description = "Pets operations")
@Validated
@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
class RegisterPetController {

    @Operation(
            summary = "Register a new pet",
            description = "Register a new pet",
            tags = {"Pets"}
    )
    @Tag(name = "Pets", description = "Register a new pet")
    @PostMapping
    void registerPet(@Valid @RequestBody RegisterPetRequest request) {

    }

    @Schema(
            name = "RegisterPetRequest",
            description = "Pet information",
            example = """
                    {
                      "name": "Blacky",
                      "breed": "Cat",
                      "color": "Black",
                      "age": 2.5
                    }
                    """
    )
    @Data
    static class RegisterPetRequest {
        @NotBlank
        @Length(min = 3, max = 100)
        private String name;
        @NotBlank
        @Pattern(regexp = Dog.PET_TYPE+"|"+ Cat.PET_TYPE)
        private String species;
        private String breed;
        private String color;
        @NotNull
        private BigDecimal age;
    }

}
