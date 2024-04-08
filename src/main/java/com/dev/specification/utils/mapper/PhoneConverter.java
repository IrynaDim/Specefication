package com.dev.specification.utils.mapper;

import com.dev.specification.model.Color;
import com.dev.specification.model.Phone;
import com.dev.specification.model.dto.PhoneDto;
import com.dev.specification.model.dto.PhonePageDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PhoneConverter {
    public PhoneDto convertToDto(Phone phone) {
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setColors(phone.getColor().stream()
                .map(Color::getName)
                .collect(Collectors.toSet()));
        phoneDto.setModel(phone.getModel().getName());
        phoneDto.setPrice(phone.getPrice());
        phoneDto.setYear(phone.getYear());
        phoneDto.setProducer(phone.getProducer().getName());
        return phoneDto;
    }

    public PhonePageDto convertToPage(Page<Phone> phones) {
        PhonePageDto phonePageDto = new PhonePageDto();
        phonePageDto.setTotalPages(phones.getTotalPages());
        phonePageDto.setTotalElements(phones.getTotalElements());
        phonePageDto.setPhones(phones.getContent().stream()
                .map(this::convertToDto).toList());
        return phonePageDto;
    }
}
