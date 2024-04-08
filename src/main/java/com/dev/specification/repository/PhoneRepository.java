package com.dev.specification.repository;

import com.dev.specification.model.Model;
import com.dev.specification.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhoneRepository extends JpaRepository<Phone, Integer>,
        JpaSpecificationExecutor<Phone> {
}
