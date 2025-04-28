package io.zutun.samples.usecases;

import io.swagger.v3.oas.annotations.media.Schema;
import io.zutun.samples.domain.Pet;
import lombok.Data;

import java.util.Optional;
import java.util.UUID;

public interface GetPetUseCase {

    Optional<Pet> getPet(UUID petId);

    @Schema(
            name = "GetPetResponse",
            description = "Pet information",
            example = """
                    {
                      "id": "123e4567-e89b-12d3-a456-426614174000",
                      "name": "Blacky",
                      "breed": "Cat",
                      "color": "Black"
                    }
                    """
    )
    @Data
    class GetPetResponse {
        private UUID id;
        private String name;
        private String breed;
        private String color;
        
        public static GetPetResponse fromPet(Pet pet) {
            GetPetResponse response = new GetPetResponse();
            response.setId(pet.getId());
            response.setName(pet.getName());
            response.setBreed(pet.getBreed());
            response.setColor(pet.getColor());
            return response;
        }
    }
}