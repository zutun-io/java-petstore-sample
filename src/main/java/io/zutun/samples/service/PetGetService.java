package io.zutun.samples.service;

import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.GetPetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetGetService implements GetPetUseCase {

    private final PetsRepository repository;

    @Override
    public Optional<Pet> getPet(GetPetQuery query) {
        return repository.findById(query.getId());
    }
}
