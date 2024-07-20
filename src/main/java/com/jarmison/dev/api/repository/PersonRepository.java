package com.jarmison.dev.api.repository;

import com.jarmison.dev.api.core.entity.PersonEntity;
import com.jarmison.dev.api.repository.query.ClientQuerys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>, ClientQuerys {

    @Query(VERIFY_CLIENT_CPF)
    boolean isthereAreRegisteredCpf(String cpf);

    @Query(VERIFY_CLIENT_EMAIL)
    boolean isthereAreRegisteredEmail(String email);
}
