package com.example.additional.service.impl;

import com.example.additional.feign.FirstService;
import com.example.additional.service.ProductService;
import com.example.objects.common.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.additional.utils.Validator.decreasePercentIsValid;
import static com.example.additional.utils.Validator.responseIdIsEmpty;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final FirstService firstService;

    public List<ProductDto> getAllProductsManufacture(String manufacturerId) {
        List<ProductDto> response = firstService.getAllProducts();
        responseIdIsEmpty(response);
        response = response.stream().filter(p -> {
            if (p.getOwner().getPassportID() != null && !p.getOwner().getPassportID().isEmpty()) {
                return p.getOwner().getPassportID().equals(manufacturerId);
            }
            return true;
        }).collect(Collectors.toList());
        return response;
    }

    public void reducePriceAllProductsByPercentage(Integer decreasePercent) {
        decreasePercentIsValid(decreasePercent);
        List<ProductDto> allProduct = firstService.getAllProducts();
        for (ProductDto productResponseDto : allProduct) {
            productResponseDto.setPrice((long) Math.ceil((double) productResponseDto.getPrice() * (100 - decreasePercent) / 100));
            firstService.updateProductById(productResponseDto.getId(), productResponseDto);
        }
    }
}
