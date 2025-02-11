package io.zutun.samples.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Accessors(chain = true)
@Getter
@Table(name = "pets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pet_type")
@Entity
public abstract class Pet extends AbstractAuditable {

    protected String name;
    protected String breed;
    @Setter
    protected String color;
    protected LocalDate birthDate;

    protected Pet(String name, String breed, LocalDate birthDate) {
        this.name = requireNonNull(name);
        this.breed = requireNonNull(breed);
        this.birthDate = requireNonNull(birthDate);
    }

}
