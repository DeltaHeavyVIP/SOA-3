package com.example.objects.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Длина строки не должна быть больше 40, Поле не может быть null
    private ColorDto eyeColor; //Поле не может быть null
    private ColorDto hairColor; //Поле может быть null
    private CountryDto nationality; //Поле может быть null
    private LocationDto location; //Поле может быть null
}
