package io.zutun.samples.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.zutun.samples.domain.Pet;
import io.zutun.samples.service.GetPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

@Tag(name = "Pets", description = "Pets operations")
@Validated
@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
public class PetController {
    
    private final GetPetService petService;
    
    record GetPetResponse(UUID id, String name, String breed, String color, LocalDate birthDate) {
        static GetPetResponse fromPet(Pet pet) {
            return new GetPetResponse(
                pet.getId(), 
                pet.getName(), 
                pet.getBreed(), 
                pet.getColor(), 
                pet.getBirthDate()
            );
        }
    }
    
    @Operation(
            summary = "Get a pet by ID",
            description = "Get a pet by ID",
            tags = {"Pets"}
    )
    @Tag(name = "Pets", description = "Get a pet by ID")
    @GetMapping("/{petId}")
    public ResponseEntity<GetPetResponse> getPetById(@PathVariable("petId") UUID petId) {
        Optional<Pet> petOptional = petService.getPet(petId);
        
        return petOptional
                .map(pet -> ResponseEntity.ok(GetPetResponse.fromPet(pet)))
                .orElse(ResponseEntity.notFound().build());
    }
}