package com.example.additional.controller;

import com.example.additional.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/ebay", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/ping")
    public String isAlive() {
        return "pong";
    }

    @GetMapping("/filter/manufacturer/{manufacturer-id}")
    public ResponseEntity<?> getAllProductsManufacture(@PathVariable("manufacturer-id") String manufacturerId) {
        return new ResponseEntity<>(productServiceImpl.getAllProductsManufacture(manufacturerId), HttpStatus.OK);
    }

    @PutMapping("/price/decrease/{decrease-percent}")
    public ResponseEntity<?> reducePriceAllProductsByPercentage(@PathVariable("decrease-percent") Integer decreasePercent) {
        productServiceImpl.reducePriceAllProductsByPercentage(decreasePercent);
        return ResponseEntity.ok(null);
    }

}
