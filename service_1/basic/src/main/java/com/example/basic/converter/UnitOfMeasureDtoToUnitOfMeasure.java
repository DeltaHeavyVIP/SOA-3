package com.example.basic.converter;

import com.example.basic.model.UnitOfMeasure;
import com.example.objects.common.UnitOfMeasureDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureDtoToUnitOfMeasure implements Converter<UnitOfMeasureDto, UnitOfMeasure> {

    @Override
    public UnitOfMeasure convert(UnitOfMeasureDto unitOfMeasureDto) {
        return UnitOfMeasure.valueOf(unitOfMeasureDto.toString());
    }

}