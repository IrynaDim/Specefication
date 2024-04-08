package com.dev.specification.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PhoneDto {
    private String producer;
    private String model;
    private Set<String> colors;
    private Double price;
    private Integer year;
}
