package com.example.objects.basic.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseDto<T> {
    private List<T> data;
    private Long totalSize;
}
