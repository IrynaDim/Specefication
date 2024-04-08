package com.dev.specification.repository.specification.phone;

import com.dev.specification.model.Phone;
import com.dev.specification.model.Producer;
import com.dev.specification.repository.specification.SpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PhoneModelSpecification implements SpecificationProvider<Phone> {
    private static final String FILTER_KEY = "model";
    private static final String FIELD_PHONE_PARAM = "model";
    private static final String FIELD_MODEL_PARAM = "name";

    @Override
    public Specification<Phone> getSpecification(String[] models) {
        return ((root, query, criteriaBuilder) -> {
            Join<Phone, Producer> join = root.join(FIELD_PHONE_PARAM, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(join.get(FIELD_MODEL_PARAM));
            for (String category : models) {
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
