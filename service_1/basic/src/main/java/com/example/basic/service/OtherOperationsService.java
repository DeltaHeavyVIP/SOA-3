package com.example.basic.service;

import com.example.basic.exception.InvalidInputDataException;
import com.example.basic.model.Product;
import com.example.basic.repositories.PersonRepo;
import com.example.basic.repositories.ProductRepo;
import com.example.objects.basic.response.AmountResponseDto;
import com.example.objects.common.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OtherOperationsService {

    private ProductRepo productRepo;

    private PersonRepo personRepo;

    private ConversionService conversionService;

    @Autowired
    public OtherOperationsService(ProductRepo productRepo, PersonRepo personRepo, ConversionService conversionService) {
        this.productRepo = productRepo;
        this.personRepo = personRepo;
        this.conversionService = conversionService;
    }

    public AmountResponseDto countProductsWherePriceHigher(Long price) {
        if (price == null || price < 1) {
            throw new InvalidInputDataException("Price should be more than 0. Invalid data request");
        }
        return new AmountResponseDto(productRepo.countAllByPriceAfter(price));
    }

    public ArrayList<ProductDto> getArrayProductsWhereNameIncludeSubstring(String subString) {
        if (subString == null || subString.isEmpty()) {
            throw new InvalidInputDataException(String.format("subString should be not null and not empty. Your substring is %s", subString));
        }
        ArrayList<ProductDto> products = new ArrayList<>();
        for (Product product : productRepo.findAllByNameContaining(subString)) {
            products.add(conversionService.convert(product, ProductDto.class));
        }
        return products;
    }

    public ArrayList<String> getArrayProductsWhereNameUnique() {
        return personRepo.findAllPersonUniqueName();
    }
}
