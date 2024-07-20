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
        if (id == null) throw new NotificationException("Pessoa sem id, padrao nao suportado....");
        this.id = id;
    }

    public void setNome(String nome) {
        if (StringUtils.isBlank(nome)) throw new NotificationException("Nome deve ser informado.....");
        if (StringUtils.equalsIgnoreCase(nome, "undefined")) throw new NotificationException("Nome invalido....");
        this.nome = nome.toUpperCase();
    }

    public void setCpf(String cpf) {
        if (StringUtils.isBlank(cpf)) throw new NotificationException("Cpf deve ser informado.....");
        if (StringUtils.equalsIgnoreCase(cpf, "undefined")) throw new NotificationException("Cpf invalido....");
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        if (StringUtils.isBlank(email)) throw new NotificationException("Email deve ser informado");
        if (StringUtils.equalsIgnoreCase(email, "undefined")) throw new NotificationException("Email invalido....");
        this.email = email;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) throw new NotificationException("");
        if (dataNascimento.isAfter(LocalDate.now())) throw new NotificationException("Data de nascimento não pode ser uma data futura....");
        this.dataNascimento = dataNascimento;
    }

    @Override
    public PersonEntity toEntity() {
        PersonEntity entity  = new PersonEntity();
        entity.setCpf(this.getCpf());
        entity.setEmail(this.getEmail());
        entity.setNome(this.getNome());
        entity.setId(this.getId());
        entity.setDataNascimento(this.getDataNascimento());
        return entity;
    }
}
