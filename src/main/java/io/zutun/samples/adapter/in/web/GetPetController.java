package io.zutun.samples.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.zutun.samples.service.PetsSearchService;
import io.zutun.samples.service.PetsSearchService.PetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Pets", description = "Pets operations")
@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
class GetPetController {

    private final PetsSearchService petsSearchService;

    @Operation(
            summary = "Get pet by ID",
            description = "Retrieve a pet by its unique identifier",
            tags = {"Pets"}
    )
    @GetMapping("/{petId}")
    ResponseEntity<PetResponse> getPetById(@PathVariable UUID petId) {
        PetResponse response = petsSearchService.getPetById(petId);
        return ResponseEntity.ok(response);
    }
}