package com.example.basic.model;

import com.example.objects.common.ColorDto;
import com.example.objects.common.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @Size(max = 40)
    @Column(name = "passport", nullable = false)
    private String passportID; //Длина строки не должна быть больше 40, Поле не может быть null

    @NotBlank
    @Column(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    @Column(name = "eyeColor")
    @Enumerated(EnumType.STRING)
    private Color eyeColor; //Поле не может быть null

    @NotNull
    @Column(name = "hairColor")
    @Enumerated(EnumType.STRING)
    private Color hairColor; //Поле может быть null

    @NotNull
    @Column(name = "nationality")
    @Enumerated(EnumType.STRING)
    private Country nationality; //Поле может быть null

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Location location; //Поле может быть null

    public ColorDto getEyeColorDtoFromPerson() {
        return ColorDto.valueOf(this.getEyeColor().toString());
    }

    public ColorDto getHairColorDtoFromPerson() {
        return ColorDto.valueOf(this.getHairColor().toString());
    }

    public CountryDto getNationalityDtoFromPerson() {
        return CountryDto.valueOf(this.getNationality().toString());
    }
}
