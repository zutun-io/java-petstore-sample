package io.zutun.samples.adapter.in.web;

import io.zutun.samples.service.PetsSearchService;
import io.zutun.samples.service.PetsSearchService.PetResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAllPetsControllerTest {

    private final PetsSearchService petsSearchService = mock(PetsSearchService.class);
    private final GetAllPetsController getAllPetsController = new GetAllPetsController(petsSearchService);
    private final GetPetController getPetController = new GetPetController(petsSearchService);

    @Test
    void testGetPetById() {
        UUID petId = UUID.randomUUID();
        PetResponse petResponse = new PetResponse(petId, "Blacky", "Cat", "Black");

        when(petsSearchService.getPetById(petId)).thenReturn(petResponse);

        ResponseEntity<PetResponse> response = getPetController.getPetById(petId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(petResponse, response.getBody());

        verify(petsSearchService, times(1)).getPetById(petId);
    }

    @Test
    void testGetAllPets() {
        Pageable pageable = PageRequest.of(0, 10);
        List<PetResponse> pets = List.of(
                new PetResponse(UUID.randomUUID(), "Blacky", "Cat", "Black"),
                new PetResponse(UUID.randomUUID(), "Buddy", "Dog", "Brown")
        );
        Page<PetResponse> petPage = new PageImpl<>(pets, pageable, pets.size());

        when(petsSearchService.getAllPets(Optional.empty(), Optional.empty(), pageable)).thenReturn(petPage);

        ResponseEntity<Page<PetResponse>> response = getAllPetsController.getAllPets(Optional.empty(), Optional.empty(), pageable);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(petPage, response.getBody());

        verify(petsSearchService, times(1)).getAllPets(Optional.empty(), Optional.empty(), pageable);
    }
}