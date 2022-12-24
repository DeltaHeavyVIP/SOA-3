package com.example.objects.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private int x;
    private Double y; //Поле не может быть null
    private double z;
}
