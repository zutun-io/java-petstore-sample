package io.zutun.samples.service;

import io.zutun.samples.domain.*;
import io.zutun.samples.service.PetsSearchService.PetResponse;
import io.zutun.samples.usecases.*;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PetsSearchServiceTest {

    private final PetsRepository petsRepository = mock(PetsRepository.class);
    private final PetsSearchService service = new PetsSearchService(petsRepository);

    @Test
    void testGetPetById() {
        Pet pet = new Cat( "Blacky","Persian", LocalDate.now().minusYears(2));

        when(petsRepository.findById(pet.getId())).thenReturn(Optional.of(pet));

        PetResponse response = service.getPetById(pet.getId());
        assertEquals(pet.getId(), response.id());
        assertEquals("Blacky", response.name());
        assertEquals("Persian", response.breed());

        verify(petsRepository, times(1)).findById(pet.getId());
    }

    @Test
    void testGetPetByIdNotFound() {
        UUID petId = UUID.randomUUID();

        when(petsRepository.findById(petId)).thenReturn(Optional.empty());

        assertThrows(PetNotFoundException.class, () -> service.getPetById(petId));

        verify(petsRepository, times(1)).findById(petId);
    }

    @Test
    void testGetAllPets() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Pet> pets = List.of(
                new Cat( "Blacky","Persian", LocalDate.now().minusYears(2)),
                new Dog( "Buddy", "Labrador", LocalDate.now().minusYears(3))
        );
        Page<Pet> petPage = new PageImpl<>(pets, pageable, pets.size());

        when(petsRepository.findByNameAndAge(null, null, pageable)).thenReturn(petPage);

        Page<PetResponse> response = service.getAllPets(Optional.empty(), Optional.empty(), pageable);
        assertEquals(2, response.getTotalElements());
        assertEquals("Blacky", response.getContent().get(0).name());
        assertEquals("Buddy", response.getContent().get(1).name());

        verify(petsRepository, times(1)).findByNameAndAge(null, null, pageable);
    }
}