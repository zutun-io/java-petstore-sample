package io.zutun.samples.service;

import io.zutun.samples.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface PetsRepository extends JpaRepository<Pet, UUID> {
}