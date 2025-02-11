package io.zutun.samples.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(Dog.PET_TYPE)
@Accessors(chain = true)
@Getter
@Entity
public class Dog extends Pet {

    public static final String PET_TYPE = "DOG";

    public Dog(String name, String breed, LocalDate birthDate) {
        super(name, breed, birthDate);
    }
}
