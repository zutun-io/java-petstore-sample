package io.zutun.samples.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(Cat.PET_TYPE)
@Accessors(chain = true)
@Getter
@Entity
public class Cat extends Pet {

    public static final String PET_TYPE = "CAT";

}
