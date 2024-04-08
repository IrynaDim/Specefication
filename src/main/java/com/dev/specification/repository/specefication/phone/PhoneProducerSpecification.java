package com.dev.specification.repository.specefication.phone;

import com.dev.specification.model.Phone;
import com.dev.specification.model.Producer;
import com.dev.specification.repository.specefication.SpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PhoneProducerSpecification implements SpecificationProvider<Phone> {
    private static final String FILTER_KEY = "producer";
    private static final String FIELD_PHONE_PARAM = "producer";
    private static final String FIELD_COLOR_PARAM = "name";

    @Override
    public Specification<Phone> getSpecification(String[] producers) {
        return ((root, query, criteriaBuilder) -> {
            Join<Phone, Producer> join = root.join(FIELD_PHONE_PARAM, JoinType.INNER);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(join.get(FIELD_COLOR_PARAM));
            for (String category : producers) {
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
