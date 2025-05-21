package io.zutun.samples.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.zutun.samples.domain.Pet;
import io.zutun.samples.exception.ErrorResponse;
import io.zutun.samples.usecases.FindPetByIdUseCase;
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
public class FindPetByIdController {

    private final FindPetByIdUseCase findPetByIdUseCase;

    @Operation(
            summary = "Get a pet by ID",
            description = "Returns a pet by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful response",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FindPetByIdResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pet not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<FindPetByIdResponse> getPetById(@PathVariable UUID id) {
        Pet pet = findPetByIdUseCase.findById(id);
        return ResponseEntity.ok(new FindPetByIdResponse(pet));
    }

    public record FindPetByIdResponse(UUID id, String name, String breed, String color, LocalDate age) {
        public FindPetByIdResponse(Pet pet) {
            this(pet.getId(), pet.getName(), pet.getBreed(), pet.getColor(), pet.getBirthDate());
        }
    }

}
