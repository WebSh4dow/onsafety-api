package com.jarmison.dev.api.mapper;

import com.jarmison.dev.api.core.entity.PersonEntity;
import com.jarmison.dev.api.core.mapper.MapperToDomain;
import com.jarmison.dev.api.core.mapper.MapperToResponse;
import com.jarmison.dev.api.domain.Person;
import com.jarmison.dev.api.request.PersonRequest;
import com.jarmison.dev.api.response.PersonResponse;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PersonMapper implements MapperToDomain<Person, PersonRequest>, MapperToResponse<PersonResponse, PersonEntity> {

    @Override
    public Person toDomain(PersonRequest request) {
        Person person = new Person();
        person.setId(request.getId());
        person.setCpf(request.getCpf());
        person.setNome(request.getNome());
        person.setDataNascimento(request.getDataNascimento());
        return person;
    }

    @Override
    public PersonResponse toResponse(PersonEntity entity) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(entity.getId());
        personResponse.setCpf(entity.getCpf());
        personResponse.setNome(entity.getNome());
        personResponse.setDataNascimento(entity.getDataNascimento());
        return personResponse;
    }

    @Override
    public List<PersonResponse> toCollections(List<PersonEntity> list) {
        return list.stream()
                .map(this::toResponse)
                .toList();
    }
}
