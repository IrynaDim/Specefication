package com.dev.specification.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PhonePageDto {
    private List<PhoneDto> phones;
    private long totalPages;
    private long totalElements;
}
