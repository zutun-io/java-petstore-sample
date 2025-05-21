package io.zutun.samples.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.FindPetByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Tag(name = "Pets", description = "Pets operations")
@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
class FindPetByIdController {

    private final FindPetByIdUseCase findPetByIdUseCase;

    @Operation(
            summary = "Get a pet by ID",
            description = "Returns a pet by its ID"
    )
    @GetMapping("/{id}")
    ResponseEntity<FindPetByIdResponse> getPetById(@PathVariable UUID id) {
        Pet pet = findPetByIdUseCase.findById(id);
        return ResponseEntity.ok(new FindPetByIdResponse(pet));
    }

    record FindPetByIdResponse(
            UUID id,
            String name,
            String breed,
            String color,
            LocalDate birthDate,
            String age) {

        public FindPetByIdResponse(Pet pet) {
            this(
                    pet.getId(),
                    pet.getName(),
                    pet.getBreed(),
                    pet.getColor(),
                    pet.getBirthDate(),
                    FindPetByIdResponse.ageFromPeriod(pet.getAge())
            );
        }

        static String ageFromPeriod(Period age) {
            if (age.getYears() > 1) {
                return String.format("%d years", age.getYears());
            }
            if (age.getMonths() > 1) {
                return String.format("%d months", age.getMonths());
            }
            return String.format("%d days", age.getDays());
        }
    }

}
