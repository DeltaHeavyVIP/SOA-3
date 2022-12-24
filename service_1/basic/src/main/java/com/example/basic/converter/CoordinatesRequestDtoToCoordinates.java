package com.example.basic.converter;

import com.example.basic.model.Coordinates;
import com.example.objects.common.CoordinatesDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CoordinatesRequestDtoToCoordinates implements Converter<CoordinatesDto, Coordinates> {
    @Override
    public Coordinates convert(CoordinatesDto coordinatesRequestDto) {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(coordinatesRequestDto.getX());
        coordinates.setY(coordinatesRequestDto.getY());
        return coordinates;
    }
}
