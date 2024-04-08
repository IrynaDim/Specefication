package com.dev.specification.repository.specification.phone;

import com.dev.specification.exception.FilterIllegalArgumentException;
import com.dev.specification.model.Phone;
import com.dev.specification.repository.specification.SpecificationManager;
import com.dev.specification.repository.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PhoneSpecificationManager implements SpecificationManager<Phone> {

    private final Map<String, SpecificationProvider<Phone>> providerMap;


    public PhoneSpecificationManager(List<SpecificationProvider<Phone>> carSpecificationList) {
        this.providerMap = carSpecificationList.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey, Function.identity()));
    }

    @Override
    public Specification<Phone> get(String filterKey, String[] params) {
        if (!providerMap.containsKey(filterKey)) {
            throw new FilterIllegalArgumentException("Key " + filterKey + " is not supported for data filtering.");
        }
        return providerMap.get(filterKey).getSpecification(params);
    }
}
