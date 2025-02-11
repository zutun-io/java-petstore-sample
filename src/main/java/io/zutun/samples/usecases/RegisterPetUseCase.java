package io.zutun.samples.usecases;

import io.swagger.v3.oas.annotations.media.Schema;
import io.zutun.samples.domain.Cat;
import io.zutun.samples.domain.Dog;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public interface RegisterPetUseCase {

    UUID registerPet(RegisterPetCommand command);

    @Schema(
            name = "RegisterPetRequest",
            description = "Pet information",
            example = """
                    {
                      "species" : "CAT",
                      "name": "Blacky",
                      "breed": "Cat",
                      "color": "Black",
                      "age": 2.5
                    }
                    """
    )
    @Data
    class RegisterPetCommand {
        @NotBlank
        @Length(min = 3, max = 100)
        private String name;
        @NotBlank
        @Pattern(regexp = Dog.PET_TYPE + "|" + Cat.PET_TYPE)
        private String species;
        private String breed;
        private String color;
        @Positive
        private int age;
    }
}
