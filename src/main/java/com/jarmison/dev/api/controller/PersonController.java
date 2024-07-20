package com.jarmison.dev.api.controller;

import com.jarmison.dev.api.core.api.PersonApi;
import com.jarmison.dev.api.core.builder.Log5wBuilder;
import com.jarmison.dev.api.core.entity.PersonEntity;
import com.jarmison.dev.api.domain.Person;
import com.jarmison.dev.api.mapper.PersonMapper;
import com.jarmison.dev.api.request.PersonRequest;
import com.jarmison.dev.api.response.PersonResponse;
import com.jarmison.dev.api.service.PersonService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/persons")
@ApiOperation("Listar auditorias")
public class PersonController implements PersonApi {

    private final PersonService personService;
    private final PersonMapper personMapper;

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @Operation(summary = "Create person ", description = "Create Person in   application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated person"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Invalid input")
    })
    @Override
    public ResponseEntity<PersonResponse> add(
            @Parameter(description = "Person request body") PersonRequest requestBody) {
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

    @Operation(summary = "Get all persons", description = "Retrieve a list of all persons.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of persons")
    })
    @Override
    public ResponseEntity<List<PersonResponse>> findAll() {
        List<PersonEntity> entities = personService.findAll();
        return ResponseEntity.ok(personMapper.toCollections(entities));
    }

    @Operation(summary = "Find person by ID", description = "Retrieve a person by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved person"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @Override
    public ResponseEntity<PersonResponse> findById(
            @Parameter(description = "ID of the person to be retrieved") @PathVariable Long id) {
        PersonEntity entity = personService.findById(id);
        return ResponseEntity.ok(personMapper.toResponse(entity));
    }

    @Operation(summary = "Delete person by ID", description = "Deletes a person by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted person"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @Override
    public void delete(
            @Parameter(description = "ID of the person to be deleted") @PathVariable Long id) {
        Log5wBuilder.Metodo.metodo("[DELETE: PERSON RESOURCE]")
                .oQueEstaAcontecendo("[REMOVENDO UMA NOVA PESSOA NO BANCO DE DADOS...]")
                .adicionaInfo("toString", id.toString())
                .info(log);
        personService.deleteById(id);
    }

    @Operation(summary = "Update person by ID", description = "Updates an existing person entry by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated person"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @Override
    public ResponseEntity<PersonResponse> update(
            @Parameter(description = "ID of the person to be updated") @PathVariable Long id,
            @Parameter(description = "Person request body with updated data") @RequestBody PersonRequest requestBody) {

        Person person = personMapper.toDomain(requestBody);
        personService.validateCpfPerson(person.getCpf());

        PersonEntity existingEntity = personService.findById(id);
        if (existingEntity == null) return ResponseEntity.notFound().build();

        existingEntity.setCpf(person.getCpf());
        existingEntity.setNome(person.getNome());
        existingEntity.setDataNascimento(person.getDataNascimento());
        existingEntity.setEmail(person.getEmail());

        Log5wBuilder.Metodo.metodo("[UPDATE: PERSON RESOURCE]")
                .oQueEstaAcontecendo("[ATUALIZANDO UMA NOVA PESSOA NO BANCO DE DADOS...]")
                .adicionaInfo("toString", person.toString())
                .info(log);

        PersonEntity updatedEntity = personService.save(existingEntity);
        PersonResponse response = personMapper.toResponse(updatedEntity);
        return ResponseEntity.ok(response);
    }
}
