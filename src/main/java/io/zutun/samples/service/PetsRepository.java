package io.zutun.samples.service;

import io.zutun.samples.domain.Pet;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface PetsRepository extends JpaRepository<Pet, UUID> {
    @Query("SELECT p FROM Pet p WHERE " +
            "(:name IS NULL OR p.name LIKE %:name%) AND " +
            "(:age IS NULL OR p.age = :age)")
    Page<Pet> findByNameAndAge(@Param("name") String name, @Param("age") Integer age, Pageable pageable);
}