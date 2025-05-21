package io.zutun.samples.usecases;

import io.swagger.v3.oas.annotations.media.Schema;
import io.zutun.samples.domain.Cat;
import io.zutun.samples.domain.Dog;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
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
                      "birthDate": "2022-06-17"
                    }
                    """
    )
    @Data
    class RegisterPetCommand {
        @NotBlank
        @Length(min = 3, max = 100, message = "Nombre de mascota debe tener entre 3 y 100 caracteres")
        private String name;
        @NotBlank
        @Pattern(regexp = Dog.PET_TYPE + "|" + Cat.PET_TYPE, message = "Tipo de mascota no válido")
        private String species;
        private String breed;
        private String color;
        @Past
        private LocalDate birthDate;
    }
}
