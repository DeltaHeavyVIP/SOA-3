package com.example.additional.utils;

import com.example.additional.exception.InvalidInputDataException;
import com.example.additional.exception.ResourceNotFoundException;
import com.example.objects.common.ProductDto;

import java.util.List;

public class Validator {

    public static void responseIdIsEmpty(List<ProductDto> response) {
        if (response.size() == 0) {
            throw new ResourceNotFoundException("Not found item");
        }
    }

    public static void decreasePercentIsValid(Integer decreasePercent) {
        if (decreasePercent <= 0 || decreasePercent > 100) {
            throw new InvalidInputDataException("Validation Failed");
        }
    }

}
