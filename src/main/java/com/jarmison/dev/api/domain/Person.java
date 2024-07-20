package com.jarmison.dev.api.domain;

import com.jarmison.dev.api.core.entity.PersonEntity;
import com.jarmison.dev.api.core.exception.notification.NotificationException;
import com.jarmison.dev.api.core.mapper.MapperToEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;

@Getter
@Setter
public class Person implements MapperToEntity<PersonEntity> {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private LocalDate dataNascimento;

    public void setPersonId(Long id) {
        if (id == null) throw new NotificationException("Pessoa sem id, padrao nao suportado.");
        this.id = id;
    }

    public void setNome(String nome) {
        if (StringUtils.isBlank(nome)) throw new RuntimeException("");
        if (StringUtils.equalsIgnoreCase(nome, "undefined")) throw new RuntimeException("");
        this.nome = nome.toUpperCase();
    }

    public void setCpf(String cpf) {
        if (StringUtils.isBlank(cpf)) throw new RuntimeException("Cpf deve ser informado.");
        if (StringUtils.equalsIgnoreCase(cpf, "undefined")) throw new RuntimeException("Cpf invalido.");
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        if (StringUtils.isBlank(email)) throw new RuntimeException("");
        if (StringUtils.equalsIgnoreCase(email, "undefined")) throw new RuntimeException("Email invalido.");
        this.email = email;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) throw new RuntimeException("");
        if (dataNascimento.isAfter(LocalDate.now())) throw new RuntimeException("Data de nascimento não pode ser uma data futura.");
        this.dataNascimento = dataNascimento;
    }

    @Override
    public PersonEntity toEntity() {
        PersonEntity entity  = new PersonEntity();
        entity.setCpf(this.getCpf());
        entity.setEmail(this.getEmail());
        entity.setNome(this.getNome());
        entity.setId(this.getId());
        return entity;
    }
}
