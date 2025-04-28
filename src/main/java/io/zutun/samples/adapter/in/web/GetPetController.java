package io.zutun.samples.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.GetPetUseCase;
import io.zutun.samples.usecases.GetPetUseCase.GetPetQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@Tag(name = "Pets", description = "Pets operations")
@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
public class GetPetController {

    private final GetPetUseCase getPetUseCase;

    @Operation(
            summary = "Get a pet by id",
            description = "Get a pet by id",
            tags = {"Pets"}
    )
    @Tag(name = "Pets", description = "Get a pet")
    @GetMapping("/{petId}")
    ResponseEntity<GetPetResponse> getPet(@PathVariable("petId") String petId) {
        try {
            GetPetQuery getPetQuery = GetPetQuery.builder().id(UUID.fromString(petId)).build();

            return getPetUseCase.getPet(getPetQuery)
                    .map( pet -> ResponseEntity.ok().body( GetPetResponse.fromDomain(pet)))
                    .orElseGet( () -> ResponseEntity.notFound().build());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    record GetPetResponse(UUID id, String name, String breed, String color, LocalDate birthDate) {
        public static GetPetResponse fromDomain(Pet pet) {
            return new GetPetResponse(pet.getId(), pet.getName(), pet.getBreed(), pet.getColor(), pet.getBirthDate());
        }
    }
}
