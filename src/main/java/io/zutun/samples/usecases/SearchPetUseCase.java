package io.zutun.samples.usecases;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import io.zutun.samples.adapter.in.web.response.PaginatedResponse;
import io.zutun.samples.domain.Pet;

public interface SearchPetUseCase {

    public PaginatedResponse<Pet> findAll(int page, int pagesize);

    public Optional<Pet> findById(UUID id);

    public PaginatedResponse<Pet> findByName(String name, int page, int pagesize);

    public PaginatedResponse<Pet> findByBirthDate(LocalDate birthDate, int page, int pagesize);

    public PaginatedResponse<Pet> findByNameAndBirthDate(String name, LocalDate birthDate, int page, int pagesize);

}
