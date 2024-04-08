package com.dev.specification.service;

import com.dev.specification.model.dto.PhonePageDto;

import java.util.Map;

public interface PhoneService {
    PhonePageDto getAll(Map<String, String> params);
}
