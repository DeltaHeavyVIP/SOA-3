package com.example.additional.service;

import com.example.objects.common.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProductsManufacture(String manufacturerId);

    void reducePriceAllProductsByPercentage(Integer decreasePercent);

}
