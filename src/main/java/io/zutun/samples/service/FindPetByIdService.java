package io.zutun.samples.service;

import io.zutun.samples.domain.Pet;
import io.zutun.samples.exception.PetNotFoundException;
import io.zutun.samples.usecases.FindPetByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FindPetByIdService  implements FindPetByIdUseCase {

    private final PetsRepository petRepository;

    @Override
    public Pet findById(UUID id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(id));
    }
}
