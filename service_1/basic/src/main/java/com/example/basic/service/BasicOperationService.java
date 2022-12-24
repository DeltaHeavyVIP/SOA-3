package com.example.basic.service;

import com.example.basic.exception.InvalidInputDataException;
import com.example.basic.model.*;
import com.example.basic.repositories.CoordinatesRepo;
import com.example.basic.repositories.LocationRepo;
import com.example.basic.repositories.PersonRepo;
import com.example.basic.repositories.ProductRepo;
import com.example.basic.utils.Filter;
import com.example.objects.basic.request.ProductRequestDto;
import com.example.objects.common.FilterDto;
import com.example.objects.common.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicOperationService {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private CoordinatesRepo coordinatesRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    public ProductDto getProductById(Integer id) {
        return conversionService.convert(productRepo.findById(id).orElseThrow(() -> new InvalidInputDataException(String.format("Product with ID %d not found", id))), ProductDto.class);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        List<ProductDto> productResponseDtoList = productList.stream().map(p -> conversionService.convert(p, ProductDto.class)).collect(Collectors.toList());

        return productResponseDtoList;
    }

    public List<ProductDto> getProductsByFilter(FilterDto filterRequestDto) {
        List<Product> productList = productRepo.findAll(Sort.by(Filter.getOrdersByNames(filterRequestDto.getOrderBy()))).stream()
                .filter(p -> {
                    if (filterRequestDto.getId() != null) {
                        return filterRequestDto.getId().equals(p.getId());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getCoordinateX() != null) {
                        return filterRequestDto.getCoordinateX().equals(p.getCoordinates().getX());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getCoordinateY() != null) {
                        return filterRequestDto.getCoordinateY().equals(p.getCoordinates().getY());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getPrice() != null) {
                        return filterRequestDto.getPrice().equals(p.getPrice());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getManufactureCost() != null) {
                        return filterRequestDto.getManufactureCost().equals(p.getManufactureCost());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getOwnerLocationX() != null) {
                        return filterRequestDto.getOwnerLocationX().equals(p.getOwner().getLocation().getX());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getOwnerLocationY() != null) {
                        return filterRequestDto.getOwnerLocationY().equals(p.getOwner().getLocation().getY());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getOwnerLocationZ() != null) {
                        return filterRequestDto.getOwnerLocationZ().equals(p.getOwner().getLocation().getZ());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getName() != null && !filterRequestDto.getName().isEmpty()) {
                        return filterRequestDto.getName().equals(p.getName());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getPartNumber() != null && !filterRequestDto.getPartNumber().isEmpty()) {
                        return filterRequestDto.getPartNumber().equals(p.getPartNumber());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getOwnerName() != null && !filterRequestDto.getOwnerName().isEmpty()) {
                        return filterRequestDto.getOwnerName().equals(p.getOwner().getName());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getOwnerPassportId() != null && !filterRequestDto.getOwnerPassportId().isEmpty()) {
                        return filterRequestDto.getOwnerPassportId().equals(p.getOwner().getPassportID());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getUnitOfMeasure() != null) {
                        return filterRequestDto.getUnitOfMeasure().equals(p.getUnitOfMeasure());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getOwnerEyeColor() != null) {
                        return filterRequestDto.getOwnerEyeColor().equals(p.getOwner().getEyeColor());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getOwnerHairColor() != null) {
                        return filterRequestDto.getOwnerHairColor().equals(p.getOwner().getHairColor());
                    }
                    return true;
                })
                .filter(p -> {
                    if (filterRequestDto.getOwnerNationality() != null) {
                        return filterRequestDto.getOwnerNationality().equals(p.getOwner().getNationality());
                    }
                    return true;
                })
                .collect(Collectors.toList());
        List<ProductDto> productResponseDtoList = productList.stream().map(p -> conversionService.convert(p, ProductDto.class)).collect(Collectors.toList());

        Pageable pageable = PageRequest.of(filterRequestDto.getPage() - 1, filterRequestDto.getPageSize());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), productResponseDtoList.size());
        Page<ProductDto> page = null;
        try {
            page = new PageImpl<>(productResponseDtoList.subList(start, end), pageable, productResponseDtoList.size());
        } catch (IllegalArgumentException ex) {
            List<ProductDto> emptyList = new ArrayList<>();
            page = new PageImpl<>(emptyList.subList(0, 0), PageRequest.of(0, 1), emptyList.size());
        }

        return page.getContent();
    }

    public ProductDto createProduct(ProductRequestDto productDto) { //catch невалидные данные для owner
        Coordinates coordinates = coordinatesRepo.findFirstByXAndY(productDto.getCoordinates().getX(), productDto.getCoordinates().getY()).orElse(conversionService.convert(productDto.getCoordinates(), Coordinates.class));
        Person person = personRepo.findFirstByPassportID(productDto.getOwner().getPassportID()).orElse(new Person(productDto.getOwner().getPassportID(), productDto.getOwner().getName(), conversionService.convert(productDto.getOwner().getEyeColor(), Color.class), conversionService.convert(productDto.getOwner().getHairColor(), Color.class), conversionService.convert(productDto.getOwner().getNationality(), Country.class), locationRepo.findFirstByXAndYAndZ(productDto.getOwner().getLocation().getX(), productDto.getOwner().getLocation().getY(), productDto.getOwner().getLocation().getZ()).orElse(conversionService.convert(productDto.getOwner().getLocation(), Location.class))));
        Product product = new Product(productDto.getName(), coordinates, productDto.getPrice(), productDto.getPartNumber(), productDto.getManufactureCost(), conversionService.convert(productDto.getUnitOfMeasure(), UnitOfMeasure.class), person);
        return conversionService.convert(productRepo.save(product), ProductDto.class);
    }

    public ProductDto updateProductById(Integer id, ProductRequestDto productDto) {
        Product product = productRepo.findById(id).orElseThrow(() -> new InvalidInputDataException(String.format("Product with ID %d not found", id)));
        product.setName(productDto.getName());
        product.setCoordinates(coordinatesRepo.findFirstByXAndY(productDto.getCoordinates().getX(), productDto.getCoordinates().getY()).orElse(conversionService.convert(productDto.getCoordinates(), Coordinates.class)));
        product.setPrice(productDto.getPrice());
        product.setPartNumber(productDto.getPartNumber());
        product.setManufactureCost(productDto.getManufactureCost());
        product.setUnitOfMeasure(conversionService.convert(productDto.getUnitOfMeasure(), UnitOfMeasure.class));
        product.setOwner(personRepo.save(new Person(productDto.getOwner().getPassportID(), productDto.getOwner().getName(), conversionService.convert(productDto.getOwner().getEyeColor(), Color.class), conversionService.convert(productDto.getOwner().getHairColor(), Color.class), conversionService.convert(productDto.getOwner().getNationality(), Country.class), locationRepo.findFirstByXAndYAndZ(productDto.getOwner().getLocation().getX(), productDto.getOwner().getLocation().getY(), productDto.getOwner().getLocation().getZ()).orElse(conversionService.convert(productDto.getOwner().getLocation(), Location.class)))));
        return conversionService.convert(productRepo.save(product), ProductDto.class);
    }

    public void deleteProductById(Integer id) {
        try {
            productRepo.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new InvalidInputDataException(String.format("Product with ID %d not found", id));
        }
    }

}
