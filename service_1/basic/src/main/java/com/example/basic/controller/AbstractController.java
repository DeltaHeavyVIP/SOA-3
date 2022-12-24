package com.example.basic.controller;

import com.example.basic.exception.InternalServerException;
import com.example.basic.exception.InvalidInputDataException;
import com.example.basic.exception.ResourceNotFoundException;
import com.example.objects.common.ExceptionDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class AbstractController {

    @ExceptionHandler({InvalidInputDataException.class, ConstraintViolationException.class})
    public ResponseEntity<ExceptionDto> handleInvalidInputDataException(Exception e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleResourceNotFoundException(ResourceNotFoundException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ExceptionDto> handleInternalServerException(InternalServerException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
