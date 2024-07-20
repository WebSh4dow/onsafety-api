package com.jarmison.dev.api.controller;

import com.jarmison.dev.api.core.api.ApiSwagger;
import com.jarmison.dev.api.core.api.PersonApi;
import com.jarmison.dev.api.core.entity.PersonEntity;
import com.jarmison.dev.api.domain.Person;
import com.jarmison.dev.api.mapper.PersonMapper;
import com.jarmison.dev.api.request.PersonRequest;
import com.jarmison.dev.api.response.PersonResponse;
import com.jarmison.dev.api.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PersonController implements PersonApi, ApiSwagger {

    private final PersonService personService;
    private final PersonMapper personMapper;

    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @Override
    public ResponseEntity<PersonResponse> add(@RequestBody PersonRequest requestBody) {
        Person person = personMapper.toDomain(requestBody);

        personService.validateCpfPerson(person.getCpf());
        personService.isthereAreRegisteredCpf(person.getCpf());
        personService.isthereAreRegisteredEmail(person.getEmail());

        PersonEntity entity = personService.save(person.toEntity());
        PersonResponse response = personMapper.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<PersonResponse>> findAll() {
        List<PersonEntity> entities = personService.findAll();
        return ResponseEntity.ok(personMapper.toCollections(entities));
    }

    @Override
    public ResponseEntity<PersonResponse> findById(@PathVariable Long id) {
        PersonEntity entity = personService.findById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(personMapper.toResponse(entity));
    }

    @Override
    public void delete(@PathVariable Long id) {
        personService.deleteById(id);
    }

    @Override
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @RequestBody PersonRequest requestBody) {
        Person person = personMapper.toDomain(requestBody);
        personService.validateCpfPerson(person.getCpf());
        PersonEntity existingEntity = personService.findById(id);

        if (existingEntity == null) {
            return ResponseEntity.notFound().build();
        }

        if (!existingEntity.getCpf().equals(person.getCpf())) {
            personService.isthereAreRegisteredCpf(person.getCpf());
        }

        if (!existingEntity.getEmail().equals(person.getEmail())) {
            personService.isthereAreRegisteredEmail(person.getEmail());
        }

        existingEntity.setCpf(person.getCpf());
        existingEntity.setNome(person.getNome());
        existingEntity.setDataNascimento(person.getDataNascimento());
        existingEntity.setEmail(person.getEmail());

        PersonEntity updatedEntity = personService.save(existingEntity);
        PersonResponse response = personMapper.toResponse(updatedEntity);
        return ResponseEntity.ok(response);
    }
}