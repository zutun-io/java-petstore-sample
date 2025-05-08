package io.zutun.samples.adapter.in.web;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.zutun.samples.adapter.in.web.response.PaginatedResponse;
import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.SearchPetUseCase;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@Tag(name = "Pets", description = "Pets search operations")
@Validated
@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
public class SearchPetController {

    private final SearchPetUseCase searchPetUseCase;

    @Operation(
            summary = "Find pet by Id",
            description = "Find pet by Id",
            tags = {"Pets"}
    )
    @Tag(name = "Pets", description = "Find pet by Id")
    @GetMapping("/{petId}")
    public ResponseEntity<Pet> findById(@PathVariable UUID petId) {
        System.out.println("findPetById: " + petId);
        Optional<Pet> pet = searchPetUseCase.findById(petId);
         return pet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @Operation(
            summary = "Find pets by given attributes",
            description = "Find pet by name and/or age",
            tags = {"Pets"}
    )
    @Tag(name = "Pets", description = "Find pet by name and/or age")
    @GetMapping
    public ResponseEntity<PaginatedResponse<Pet>> findByAttributes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @Min(0) Integer age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        PaginatedResponse<Pet> pets;
        PetData petData = new PetData(name, age);

        switch(petData) {
            case PetData p when p.name() != null && p.age() != null -> {
                System.out.println("Buscar por nombre y fnac: " + p.name() + " " + calculateBirthdate(p.age()));
                pets = searchPetUseCase.findByNameAndBirthDate(p.name(), calculateBirthdate(p.age()), page, size);
            }
            case PetData p when p.name() != null -> {
                System.out.println("Buscar por nombre: " + p.name());
                pets = searchPetUseCase.findByName(p.name(), page, size);
            }
            case PetData p when p.age() != null -> {
                System.out.println("Buscar por fnac: " + calculateBirthdate(p.age()));
                pets = searchPetUseCase.findByBirthDate(calculateBirthdate(p.age()), page, size);
            }
            default -> pets = searchPetUseCase.findAll(page, size);
        }
        if(pets.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    private static LocalDate calculateBirthdate(int age) {
        return LocalDate.now().minusMonths(age * 12);
    }

}

record PetData(String name, Integer age) {}

