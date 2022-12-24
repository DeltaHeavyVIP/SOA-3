package com.example.objects.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDto {
    private float x; //Максимальное значение поля: 791
    private Float y; //Поле не может быть null
}
