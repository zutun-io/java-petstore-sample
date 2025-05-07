package io.zutun.samples.service;

import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PetsSearchService {

    private final PetsRepository petRepository;

    public PetResponse getPetById(UUID petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with ID: " + petId));
        return new PetResponse(pet.getId(), pet.getName(), pet.getBreed(), pet.getColor());
    }

    public Page<PetResponse> getAllPets(Optional<String> name, Optional<Integer> age, Pageable pageable) {
        return petRepository.findByNameAndAge(name.orElse(null), age.orElse(null), pageable)
                .map(pet -> new PetResponse(pet.getId(), pet.getName(), pet.getBreed(), pet.getColor()));
    }

    public record PetResponse(UUID id, String name, String breed, String color) {
    }
}