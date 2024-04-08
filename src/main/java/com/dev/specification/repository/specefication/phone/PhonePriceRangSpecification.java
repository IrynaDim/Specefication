package com.dev.specification.repository.specefication.phone;

import com.dev.specification.model.Phone;
import com.dev.specification.repository.specefication.SpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PhonePriceRangSpecification implements SpecificationProvider<Phone> {
    private static final String FILTER_KEY = "price";
    private static final String FIELD_PARAM = "price";

    @Override
    public Specification<Phone> getSpecification(String[] params) {
        Optional<String> minPriceOpt = Optional.ofNullable(
                params.length > 0 ? params[0] : null
        ).filter(year -> !year.trim().isEmpty());

        Optional<String> maxPriceOpt = Optional.ofNullable(
                params.length > 1 ? params[1] : null
        ).filter(year -> !year.trim().isEmpty());

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            minPriceOpt.ifPresent(min -> predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(root.get(FIELD_PARAM), Double.parseDouble(min.trim())))
            );
            maxPriceOpt.ifPresent(max -> predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(root.get(FIELD_PARAM), Double.parseDouble(max.trim())))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}