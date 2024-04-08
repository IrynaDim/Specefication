package com.dev.specification.repository.specification;

import com.dev.specification.model.Phone;
import com.dev.specification.repository.PhoneRepository;
import com.dev.specification.repository.specification.phone.PhoneYearSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        PhoneYearSpecification.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/create_table.sql", "/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class PhoneYearSpecificationIT {
    @Autowired
    private PhoneRepository phoneRepository;

    private static final String YEAR_2022 = "2022";
    private static final String YEAR_2024 = "2024";
    private static final String YEAR_NOT_EXIST = "1100";

    @Test
    public void getAllPhone_shouldReturnPhoneByYearParameter_whenDataExist() {
        PhoneYearSpecification specification = new PhoneYearSpecification();
        Specification<Phone> carSpecification = specification.getSpecification(new String[]{YEAR_2022});
        Page<Phone> phonePage = phoneRepository.findAll(carSpecification, PageRequest.of(0, 10));
        assertFalse(phonePage.isEmpty());
        assertTrue(phonePage.getContent().stream().allMatch(phone -> phone.getYear().equals(Integer.valueOf(YEAR_2022))));

    }

    @Test
    public void getAllPhone_shouldReturnPhoneByYearsParameter_whenDataExist() {
        PhoneYearSpecification specification = new PhoneYearSpecification();
        Specification<Phone> carSpecification = specification.getSpecification(new String[]{YEAR_2022, YEAR_2024});
        Page<Phone> phonePage = phoneRepository.findAll(carSpecification, PageRequest.of(0, 10));
        assertFalse(phonePage.isEmpty());
        assertTrue(phonePage.getContent().stream().allMatch(phone -> phone.getYear().equals(Integer.valueOf(YEAR_2022))
                || phone.getYear().equals(Integer.valueOf(YEAR_2024))));

    }

    @Test
    public void getAllPhone_shouldReturnPhoneByYearParameter_emptyResult() {
        PhoneYearSpecification specification = new PhoneYearSpecification();
        Specification<Phone> carSpecification = specification.getSpecification(new String[]{YEAR_NOT_EXIST});
        Page<Phone> phonePage = phoneRepository.findAll(carSpecification, PageRequest.of(0, 10));
        assertTrue(phonePage.isEmpty());

    }

}
