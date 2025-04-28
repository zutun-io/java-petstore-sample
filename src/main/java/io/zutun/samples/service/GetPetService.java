package io.zutun.samples.service;

import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.GetPetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetPetService implements GetPetUseCase {
    
    private final PetsRepository repository;
    
    @Override
    public Optional<Pet> getPet(UUID id) {
        return repository.findById(id);
    }
}