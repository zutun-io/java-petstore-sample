package io.zutun.samples.exception;


import java.util.UUID;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(UUID id) {
        super("Pet not found with ID: " + id.toString());
    }
}