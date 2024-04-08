package com.dev.specification.controller;

import com.dev.specification.model.dto.PhonePageDto;
import com.dev.specification.service.PhoneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    public PhonePageDto getAllUsers(@RequestParam(required = false) Map<String, String> filters) {
        return phoneService.getAll(filters);
    }
}
