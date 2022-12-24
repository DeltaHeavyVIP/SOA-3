package com.example.basic.converter;

import com.example.basic.model.Location;
import com.example.objects.common.LocationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocationRequestDtoToLocation implements Converter<LocationDto, Location> {
    @Override
    public Location convert(LocationDto locationRequestDto) {
        Location location = new Location();
        location.setX(locationRequestDto.getX());
        location.setY(locationRequestDto.getY());
        location.setZ(locationRequestDto.getZ());
        return location;
    }
}
