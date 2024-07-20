package com.jarmison.dev.api.controller;

import com.jarmison.dev.api.core.api.ApiSwagger;
import com.jarmison.dev.api.core.api.PersonApi;
import com.jarmison.dev.api.core.builder.Log5wBuilder;
import com.jarmison.dev.api.core.entity.PersonEntity;
import com.jarmison.dev.api.domain.Person;
import com.jarmison.dev.api.mapper.PersonMapper;
import com.jarmison.dev.api.request.PersonRequest;
import com.jarmison.dev.api.response.PersonResponse;
import com.jarmison.dev.api.service.PersonService;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController implements PersonApi, ApiSwagger {

    private final PersonService personService;
    private final PersonMapper personMapper;
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @Override
    public ResponseEntity<PersonResponse> add(@RequestBody PersonRequest requestBody) {
        Person person = personMapper.toDomain(requestBody);
        personService.validateCpfPerson(person.getCpf());

        Log5wBuilder.Metodo.metodo("[ADD: PERSON RESOURCE]")
                .oQueEstaAcontecendo("[CADASTRANDO UMA NOVA PESSOA NO BANCO DE DADOS...]")
                .adicionaInfo("toString", person.toString())
                .info(log);

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
        Log5wBuilder.Metodo.metodo("[DELETE: PERSON RESOURCE]")
                .oQueEstaAcontecendo("[REMOVENDO UMA PESSOA NO BANCO DE DADOS...]")
                .adicionaInfo("toString", id.toString())
                .info(log);
        personService.deleteById(id);
    }

    @Override
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @RequestBody PersonRequest requestBody) {
        Person person = personMapper.toDomain(requestBody);
        personService.validateCpfPerson(person.getCpf());

        PersonEntity existingEntity = personService.findById(id);
        if (existingEntity == null) return ResponseEntity.notFound().build();

        existingEntity.setCpf(person.getCpf());
        existingEntity.setNome(person.getNome());
        existingEntity.setDataNascimento(person.getDataNascimento());
        existingEntity.setEmail(person.getEmail());

        Log5wBuilder.Metodo.metodo("[UPDATE: PERSON RESOURCE]")
                .oQueEstaAcontecendo("[ATUALIZANDO UMA PESSOA NO BANCO DE DADOS...]")
                .adicionaInfo("toString", person.toString())
                .info(log);

        PersonEntity updatedEntity = personService.save(existingEntity);
        PersonResponse response = personMapper.toResponse(updatedEntity);
        return ResponseEntity.ok(response);
    }
}
