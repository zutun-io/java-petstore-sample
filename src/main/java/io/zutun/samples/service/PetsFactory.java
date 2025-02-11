package io.zutun.samples.service;

import io.zutun.samples.domain.Cat;
import io.zutun.samples.domain.Dog;
import io.zutun.samples.domain.Pet;
import io.zutun.samples.usecases.RegisterPetUseCase.RegisterPetCommand;

import java.time.LocalDate;

public class PetsFactory {

    public static Pet createPet(RegisterPetCommand registerCommand) {
        if (registerCommand.getSpecies().equals(Cat.PET_TYPE)) {
            return new Cat(
                    registerCommand.getName(),
                    registerCommand.getBreed(),
                    calculateBirthdate(registerCommand)
            );
        } else if (registerCommand.getSpecies().equals(Dog.PET_TYPE)) {
            return new Dog(
                    registerCommand.getName(),
                    registerCommand.getBreed(),
                    calculateBirthdate(registerCommand)
            );
        }
        throw new IllegalArgumentException("Invalid species");
    }

    private static LocalDate calculateBirthdate(RegisterPetCommand registerCommand) {
        return LocalDate.now().minusMonths(toMonths(registerCommand));
    }

    private static int toMonths(RegisterPetCommand registerCommand) {
        return registerCommand.getAge() * 12;
    }

}
