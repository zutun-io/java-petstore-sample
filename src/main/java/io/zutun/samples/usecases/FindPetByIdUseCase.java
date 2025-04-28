package io.zutun.samples.usecases;

import io.zutun.samples.domain.Pet;

import java.util.UUID;

public interface FindPetByIdUseCase {
    Pet findById(UUID id);
}
