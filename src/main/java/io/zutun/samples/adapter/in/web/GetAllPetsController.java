package io.zutun.samples.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.zutun.samples.service.PetsSearchService;
import io.zutun.samples.service.PetsSearchService.PetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "Pets", description = "Pets operations")
@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
class GetAllPetsController {

    private final PetsSearchService petsSearchService;

    @Operation(
            summary = "Get all pets with filters and pagination",
            description = "Retrieve a paginated list of pets filtered by name and age",
            tags = {"Pets"}
    )
    @GetMapping
    ResponseEntity<Page<PetResponse>> getAllPets(
            @RequestParam Optional<String> name,
            @RequestParam Optional<Integer> age,
            Pageable pageable) {
        Page<PetResponse> response = petsSearchService.getAllPets(name, age, pageable);
        return ResponseEntity.ok(response);
    }
}