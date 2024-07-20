package com.jarmison.dev.api.service.impl;

import com.jarmison.dev.api.core.entity.PersonEntity;
import com.jarmison.dev.api.core.exception.notification.NotificationException;
import com.jarmison.dev.api.core.service.impl.AbstractServiceImpl;
import com.jarmison.dev.api.core.validation.CpfValidator;
import com.jarmison.dev.api.repository.PersonRepository;
import com.jarmison.dev.api.service.PersonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceImpl extends AbstractServiceImpl<PersonEntity, Long> implements PersonService {

    private final CpfValidator cpfValidator;

    public PersonServiceImpl(JpaRepository<PersonEntity, Long> repository, CpfValidator cpfValidator) {
        super(repository);
        this.cpfValidator = cpfValidator;
    }

    @Override
    public void validateCpfPerson(String cpfPerson) {
        if (!cpfValidator.validateCpf(cpfPerson)) throw new NotificationException("O CPF informado é inválido.");
    }
}
