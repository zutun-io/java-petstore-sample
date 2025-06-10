package io.zutun.samples.service;

import io.zutun.samples.domain.Pet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
interface PetsRepository extends JpaRepository<Pet, UUID> {

    Page<Pet> findByNameIgnoreCase(String name, Pageable pageable);
    
    Page<Pet> findByBirthDate(LocalDate birthDate, Pageable pageable);

    Page<Pet> findByNameIgnoreCaseAndBirthDate(String name, LocalDate birthDate, Pageable pageable);

}