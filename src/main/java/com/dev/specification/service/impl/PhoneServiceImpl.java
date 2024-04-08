package com.dev.specification.service.impl;

import com.dev.specification.model.Phone;
import com.dev.specification.model.dto.PhonePageDto;
import com.dev.specification.repository.PhoneRepository;
import com.dev.specification.repository.specification.SpecificationManager;
import com.dev.specification.service.PhoneService;
import com.dev.specification.utils.FilterUtils;
import com.dev.specification.utils.mapper.PhoneConverter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final PhoneConverter phoneConverter;
    private final SpecificationManager<Phone> specificationManager;
    private final FilterUtils filterUtils;

    private static final String SPLIT_TO_ARRAY = ",";

    public PhoneServiceImpl(PhoneRepository phoneRepository,
                            PhoneConverter phoneConverter,
                            SpecificationManager<Phone> specificationManager, FilterUtils filterUtils) {
        this.phoneRepository = phoneRepository;
        this.phoneConverter = phoneConverter;
        this.specificationManager = specificationManager;
        this.filterUtils = filterUtils;
    }

    @Override
    public PhonePageDto getAll(Map<String, String> filters) {
        Pageable pageRequest = filterUtils.getPageWithFilter(filters);
        Specification<Phone> specification = null;
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            Specification<Phone> sp = specificationManager.get(entry.getKey(),
                    entry.getValue().split(SPLIT_TO_ARRAY));
            specification = specification == null ? Specification.where(sp) : specification.and(sp);
        }
        return phoneConverter.convertToPage(phoneRepository.findAll(specification, pageRequest));
    }
}
