package com.example.additional.service.impl;

import com.example.additional.config.RibbonClientConfig;
import com.example.additional.service.ProductService;
import com.example.objects.common.ProductDto;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.additional.utils.Validator.decreasePercentIsValid;
import static com.example.additional.utils.Validator.responseIdIsEmpty;

@Service
@RibbonClient(name = "basic", configuration = RibbonClientConfig.class)
public class ProductServiceImpl implements ProductService {

    private final RestTemplate restTemplate;

    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductDto> getAllProductsManufacture(String manufacturerId) {
        List<ProductDto> response = List.of(Objects.requireNonNull(restTemplate.getForObject("http://basic:8080/api/v1/products", ProductDto[].class)));
        response = response.stream().filter(p -> {
            if (p.getOwner().getPassportID() != null && !p.getOwner().getPassportID().isEmpty()) {
                return p.getOwner().getPassportID().equals(manufacturerId);
            }
            return true;
        }).collect(Collectors.toList());
        responseIdIsEmpty(response);
        return response;
    }

    public void reducePriceAllProductsByPercentage(Integer decreasePercent) {
        decreasePercentIsValid(decreasePercent);
        List<ProductDto> allProduct = List.of(Objects.requireNonNull(restTemplate.getForObject("http://basic:8080/api/v1/products", ProductDto[].class)));
        for (ProductDto productResponseDto : allProduct) {
            productResponseDto.setPrice((long) Math.ceil((double) productResponseDto.getPrice() * (100 - decreasePercent) / 100));
            Map<String, Integer> urlParams = new HashMap<>();
            urlParams.put("id", productResponseDto.getId());
            restTemplate.put("http://basic:8080/api/v1/products/{id}", productResponseDto, urlParams);
        }
    }
}
