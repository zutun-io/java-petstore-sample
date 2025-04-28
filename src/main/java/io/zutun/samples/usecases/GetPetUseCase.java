package io.zutun.samples.usecases;

import io.zutun.samples.domain.Pet;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;
import java.util.UUID;

public interface GetPetUseCase {

    Optional<Pet> getPet(GetPetQuery query);


    @Data
    @Builder
    class GetPetQuery {
        @NotBlank
        private UUID id;
    }

}
