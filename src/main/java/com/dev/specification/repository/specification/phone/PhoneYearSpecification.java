package com.dev.specification.repository.specification.phone;

import com.dev.specification.model.Phone;
import com.dev.specification.repository.specification.SpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PhoneYearSpecification implements SpecificationProvider<Phone> {
    private static final String FILTER_KEY = "year";
    private static final String FIELD_PARAM = "year";

    @Override
    public Specification<Phone> getSpecification(String[] years) {
        return ((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(FIELD_PARAM));
            for (String category : years) {
                predicate.value(category);
            }
            return criteriaBuilder.and(predicate);
        });
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
