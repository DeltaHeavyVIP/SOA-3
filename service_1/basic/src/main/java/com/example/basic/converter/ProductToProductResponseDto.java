package com.example.basic.converter;

import com.example.basic.model.Product;
import com.example.objects.common.CoordinatesDto;
import com.example.objects.common.LocationDto;
import com.example.objects.common.PersonDto;
import com.example.objects.common.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductResponseDto implements Converter<Product, ProductDto> {
    @Override
    public ProductDto convert(Product product) {
        CoordinatesDto coordinatesRequestDto = new CoordinatesDto();
        coordinatesRequestDto.setX(product.getCoordinates().getX());
        coordinatesRequestDto.setY(product.getCoordinates().getY());

        LocationDto locationRequestDto = new LocationDto();
        locationRequestDto.setX(product.getOwner().getLocation().getX());
        locationRequestDto.setY(product.getOwner().getLocation().getY());
        locationRequestDto.setZ(product.getOwner().getLocation().getZ());

        PersonDto personRequestDto = new PersonDto();
        personRequestDto.setName(product.getOwner().getName());
        personRequestDto.setPassportID(product.getOwner().getPassportID());
        personRequestDto.setEyeColor(product.getOwner().getEyeColorDtoFromPerson());
        personRequestDto.setHairColor(product.getOwner().getHairColorDtoFromPerson());
        personRequestDto.setNationality(product.getOwner().getNationalityDtoFromPerson());
        personRequestDto.setLocation(locationRequestDto);

        ProductDto productResponseDto = new ProductDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setCoordinates(coordinatesRequestDto);
        productResponseDto.setCreationDate(product.getCreationDate());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setPartNumber(product.getPartNumber());
        productResponseDto.setManufactureCost(product.getManufactureCost());
        productResponseDto.setUnitOfMeasure(product.getUnitOfMeasureDtoFromProduct());
        productResponseDto.setOwner(personRequestDto);
        return productResponseDto;
    }
}
