package com.dev.specification.service.impl;

import com.dev.specification.model.Phone;
import com.dev.specification.model.dto.PhonePageDto;
import com.dev.specification.repository.PhoneRepository;
import com.dev.specification.repository.specefication.SpecificationManager;
import com.dev.specification.service.PhoneService;
import com.dev.specification.utils.FilterUtils;
import com.dev.specification.utils.mapper.PhoneConverter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final PhoneConverter phoneConverter;
    private final SpecificationManager<Phone> specificationManager;
    private static final String SPLIT_TO_ARRAY = ",";

    public PhoneServiceImpl(PhoneRepository phoneRepository,
                            PhoneConverter phoneConverter,
                            SpecificationManager<Phone> specificationManager) {
        this.phoneRepository = phoneRepository;
        this.phoneConverter = phoneConverter;
        this.specificationManager = specificationManager;
    }

    @Override
    public PhonePageDto getAll(Map<String, String> filters) {
        Pageable pageRequest = FilterUtils.getPageWithFilter(filters);
        Specification<Phone> specification = null;
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            Specification<Phone> sp = specificationManager.get(entry.getKey(),
                    entry.getValue().split(SPLIT_TO_ARRAY));
            specification = specification == null ? Specification.where(sp) : specification.and(sp);
        }
        return phoneConverter.convertToPage(phoneRepository.findAll(specification, pageRequest));
    }

    @Override
    public List<Phone> getAll() {
        return phoneRepository.findAll();
    }
}
