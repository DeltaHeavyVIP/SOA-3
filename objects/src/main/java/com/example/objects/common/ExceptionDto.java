package com.example.objects.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionDto {
    private String message;
    private LocalDateTime timeStamp;
}
