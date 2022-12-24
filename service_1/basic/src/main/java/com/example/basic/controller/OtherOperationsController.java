package com.example.basic.controller;

import com.example.basic.service.OtherOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class OtherOperationsController extends AbstractController {

    @Autowired
    private OtherOperationsService otherOperationsService;

    @GetMapping("/count/products/price_high_parameter")
    public ResponseEntity<?> countProductsWherePriceHigher(@RequestParam(name = "price") Long price) {
        return new ResponseEntity<>(otherOperationsService.countProductsWherePriceHigher(price), HttpStatus.OK);
    }

    @GetMapping("/search/products/name/include/substring")
    public ResponseEntity<?> getArrayProductsWhereNameIncludeSubstring(@RequestParam(name = "subString") String subString) {
        return new ResponseEntity<>(otherOperationsService.getArrayProductsWhereNameIncludeSubstring(subString), HttpStatus.OK);
    }

    @GetMapping("/search/products/name/unique")
    public ResponseEntity<?> getArrayProductsWhereNameUnique() {
        return new ResponseEntity<>(otherOperationsService.getArrayProductsWhereNameUnique(), HttpStatus.OK);
    }
}
