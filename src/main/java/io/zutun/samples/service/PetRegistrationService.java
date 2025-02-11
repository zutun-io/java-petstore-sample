package io.zutun.samples.service;

import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.RegisterPetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class PetRegistrationService implements RegisterPetUseCase {

    private final PetsRepository repository;

    @Override
    public UUID registerPet(RegisterPetCommand command) {
        Pet pet = PetsFactory.createPet(command);
        return repository.save(pet).getId();
    }

}
