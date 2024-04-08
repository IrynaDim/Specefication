package com.dev.specification.service.impl;

import com.dev.specification.model.dto.PhonePageDto;
import com.dev.specification.service.PhoneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Sql(
        scripts = {"/sql/create_table.sql", "/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PhoneServiceImplIT {

    @Autowired
    PhoneService phoneService;

    private final Map<String, String> params = new HashMap<>();
    private final static String SAMSUNG_PRODUCER = "Samsung";

    @Test
    @Transactional
    public void getAll_shouldReturnPhonesByFilters_whenDataExist() {
        params.put("price", "799,");
        params.put("producer", SAMSUNG_PRODUCER);

        PhonePageDto phonePage = phoneService.getAll(params);

        assertFalse(phonePage.getPhones().isEmpty());
        assertTrue(phonePage.getPhones().stream().allMatch(phone -> phone.getProducer().equals(SAMSUNG_PRODUCER)
                && phone.getPrice() >= 799));
    }
}
