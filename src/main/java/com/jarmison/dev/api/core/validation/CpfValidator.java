package com.jarmison.dev.api.core.validation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CpfValidator {

    private final JdbcTemplate jdbcTemplate;

    public CpfValidator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean validateCpf(String cpf) {
        Integer result = jdbcTemplate.queryForObject("SELECT validate_cpf(?)", new Object[]{cpf}, Integer.class);
        return result != null && result == 1;
    }
}
