package com.jarmison.dev.api.repository.query;

public interface ClientQuerys {

    final String VERIFY_CLIENT_CPF = "SELECT CASE WHEN COUNT(pe) > 0 THEN TRUE ELSE FALSE END FROM PersonEntity pe WHERE pe.email = ?1";

    final String VERIFY_CLIENT_EMAIL = "SELECT CASE WHEN COUNT(pe) > 0 THEN TRUE ELSE FALSE END FROM PersonEntity pe WHERE pe.email = ?1";

}
