package com.example.objects.common;

import lombok.Data;

@Data
public class ProductDto {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private CoordinatesDto coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long price; //Поле не может быть null, Значение поля должно быть больше 0
    private String partNumber; //Поле может быть null
    private Integer manufactureCost; //Поле не может быть null
    private UnitOfMeasureDto unitOfMeasure; //Поле может быть null
    private PersonDto owner; //Поле не может быть null
}
