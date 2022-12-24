package com.example.additional.controller;

import com.example.additional.exception.InvalidInputDataException;
import com.example.additional.exception.ResourceNotFoundException;
import com.example.objects.common.ExceptionDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class AbstractController {

    @ExceptionHandler(InvalidInputDataException.class)
    public ResponseEntity<ExceptionDto> handleInvalidInputDataException(InvalidInputDataException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleResourceNotFoundException(ResourceNotFoundException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<ExceptionDto> buildErrorResponse(String msg, HttpStatus status) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ExceptionDto response = new ExceptionDto();

        response.setMessage(msg);
        response.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(status).headers(httpHeaders).body(response);
    }
}
