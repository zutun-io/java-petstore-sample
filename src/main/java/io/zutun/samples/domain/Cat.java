package io.zutun.samples.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(Cat.PET_TYPE)
@Accessors(chain = true)
@Getter
@Entity
public class Cat extends Pet {

    public static final String PET_TYPE = "CAT";

    public Cat(String name, String breed, LocalDate birthDate) {
        super(name, breed, birthDate);
    }
}
