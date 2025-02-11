package io.zutun.samples.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.zutun.samples.usecases.RegisterPetUseCase;
import io.zutun.samples.usecases.RegisterPetUseCase.RegisterPetCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@Tag(name = "Pets", description = "Pets operations")
@Validated
@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
class RegisterPetController {

    private final RegisterPetUseCase registerPetUseCase;

    @Operation(
            summary = "Register a new pet",
            description = "Register a new pet",
            tags = {"Pets"}
    )
    @Tag(name = "Pets", description = "Register a new pet")
    @PostMapping
    ResponseEntity<RegisterPetResponse> registerPet(@Valid @RequestBody RegisterPetCommand request) {
        RegisterPetResponse response = new RegisterPetResponse(registerPetUseCase.registerPet(request));
        return ResponseEntity
                .created(URI.create("/v1/pets/" + response.id()))
                .body(response);
    }

    record RegisterPetResponse(UUID id) {
    }

}
