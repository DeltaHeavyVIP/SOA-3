package com.example.additional.feign;

import com.example.objects.common.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "first-service", url = "http://localhost:8080/api/v1")
public interface FirstService {
    String URL_UPDATE_PRODUCT = "/products/{id}";

    String URL_GET_ALL_PRODUCT = "/products";

    @GetMapping(URL_GET_ALL_PRODUCT)
    List<ProductDto> getAllProducts();

    @PutMapping(URL_UPDATE_PRODUCT)
    ProductDto updateProductById(@PathVariable Integer id, @RequestBody ProductDto productRequestDto);

}
