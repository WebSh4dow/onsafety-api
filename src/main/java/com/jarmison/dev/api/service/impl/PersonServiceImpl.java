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
    private final PersonRepository personRepository;

    public PersonServiceImpl(JpaRepository<PersonEntity, Long> repository, CpfValidator cpfValidator, PersonRepository personRepository) {
        super(repository);
        this.cpfValidator = cpfValidator;
        this.personRepository = personRepository;
    }

    @Override
    public void validateCpfPerson(String cpfPerson) {
        if (!cpfValidator.validateCpf(cpfPerson)) throw new NotificationException("O CPF informado é inválido.");
    }

    @Override
    public void isthereAreRegisteredCpf(String cpf) {
        if (personRepository.isthereAreRegisteredCpf(cpf)) {
            throw new NotificationException("O cpf Nao pode ser cadastrado no sistema pois ja existe.");
        }
    }

    @Override
    public void isthereAreRegisteredEmail(String email) {
        if (personRepository.isthereAreRegisteredEmail(email)) {
            throw new NotificationException("O email Nao pode ser cadastrado no sistema pois ja existe.");
        }
    }
}
