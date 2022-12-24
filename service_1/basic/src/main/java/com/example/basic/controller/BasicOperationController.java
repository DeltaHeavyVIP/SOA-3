package com.example.basic.controller;

import com.example.basic.service.BasicOperationService;
import com.example.objects.basic.request.ProductRequestDto;
import com.example.objects.common.FilterDto;
import com.example.objects.common.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class BasicOperationController {

    @Autowired
    private BasicOperationService basicOperationService;

    @GetMapping("/ping")
    public ResponseEntity<?> isAlive() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(basicOperationService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/products/filter")
    public ResponseEntity<List<ProductDto>> getProductsByFilter(@RequestBody FilterDto filter) {
        return new ResponseEntity<>(basicOperationService.getProductsByFilter(filter), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto product) {
        return new ResponseEntity<>(basicOperationService.createProduct(product), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        return new ResponseEntity<>(basicOperationService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable Integer id, @RequestBody ProductRequestDto product) {
        return new ResponseEntity<>(basicOperationService.updateProductById(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer id) {
        basicOperationService.deleteProductById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}