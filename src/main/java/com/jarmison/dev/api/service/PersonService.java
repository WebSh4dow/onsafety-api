package com.jarmison.dev.api.service;

import com.jarmison.dev.api.core.entity.PersonEntity;
import com.jarmison.dev.api.core.service.AbstractService;

public interface PersonService extends AbstractService<PersonEntity, Long> {
    void validateCpfPerson(String cpfPerson);
    void isthereAreRegisteredCpf(String cpf);
    void isthereAreRegisteredEmail(String email);
}
