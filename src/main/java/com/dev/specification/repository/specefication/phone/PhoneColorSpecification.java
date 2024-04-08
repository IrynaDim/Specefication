package com.dev.specification.repository.specefication.phone;

import com.dev.specification.model.Color;
import com.dev.specification.model.Phone;
import com.dev.specification.repository.specefication.SpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.SetJoin;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class PhoneColorSpecification implements SpecificationProvider<Phone> {
    private static final String FILTER_KEY = "color";
    private static final String FIELD_PHONE_PARAM = "color";
    private static final String FIELD_COLOR_PARAM = "name";

    @Override
    public Specification<Phone> getSpecification(String[] colors) {
        return ((root, query, criteriaBuilder) -> {
            SetJoin<Phone, Color> join = root.joinSet(FIELD_PHONE_PARAM, JoinType.LEFT);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(join.get(FIELD_COLOR_PARAM));
            for (String category : colors) {
                predicate.value(category.toUpperCase());
            }
            return criteriaBuilder.and(predicate);
        });
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
