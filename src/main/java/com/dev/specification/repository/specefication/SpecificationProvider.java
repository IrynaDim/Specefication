package com.dev.specification.repository.specefication;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider <T> {

    Specification<T> getSpecification(String[] params);

    String getFilterKey();
}

