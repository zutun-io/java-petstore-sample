package io.zutun.samples.service;

import io.zutun.samples.domain.Cat;
import io.zutun.samples.domain.Dog;
import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.RegisterPetUseCase.RegisterPetCommand;

import java.util.Optional;

class PetsFactory {

    static Pet createPet(RegisterPetCommand registerCommand) {
        Pet pet = createBasicPet(registerCommand);
        fillOptionalFields(pet, registerCommand);
        return pet;
    }

    private static void fillOptionalFields(Pet pet, RegisterPetCommand registerCommand) {
        Optional.ofNullable(registerCommand.getColor()).ifPresent(pet::setColor);
    }

    private static Pet createBasicPet(RegisterPetCommand registerCommand) {
        if (registerCommand.getSpecies().equals(Cat.PET_TYPE)) {
            return new Cat(
                    registerCommand.getName(),
                    registerCommand.getBreed(),
                    registerCommand.getBirthDate()
            );
        }
        if (registerCommand.getSpecies().equals(Dog.PET_TYPE)) {
            return new Dog(
                    registerCommand.getName(),
                    registerCommand.getBreed(),
                    registerCommand.getBirthDate()
            );
        }
        throw new IllegalArgumentException("Invalid species");
    }

}
