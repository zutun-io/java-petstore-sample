package io.zutun.samples.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

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
    protected String color;
    protected LocalDate birthDate;

}
