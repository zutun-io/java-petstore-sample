package io.zutun.samples.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.zutun.samples.adapter.in.web.response.PaginatedResponse;
import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.SearchPetUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetSearchService implements SearchPetUseCase {

    private final PetsRepository repository;

    public Optional<Pet> findById(UUID id) {
        return repository.findById(id);
    }

    public PaginatedResponse<Pet> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pet> result = repository.findAll(pageable);
        return new PaginatedResponse<>(result.getContent(), page, size, result.getTotalElements(), result.getTotalPages());
    }

    public PaginatedResponse<Pet> findByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pet> result = repository.findByNameIgnoreCase(name, pageable);
        return new PaginatedResponse<>(result.getContent(), page, size, result.getTotalElements(), result.getTotalPages());
    }

    public PaginatedResponse<Pet> findByBirthDate(LocalDate birthDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pet> result = repository.findByBirthDate(birthDate, pageable);
        return new PaginatedResponse<>(result.getContent(), page, size, result.getTotalElements(), result.getTotalPages());
    }
    
    public PaginatedResponse<Pet> findByNameAndBirthDate(String name, LocalDate birthDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pet> result = repository.findByNameIgnoreCaseAndBirthDate(name, birthDate, pageable);
        return new PaginatedResponse<>(result.getContent(), page, size, result.getTotalElements(), result.getTotalPages());
    } 
}

