package com.jarmison.dev.api.repository;

import com.jarmison.dev.api.core.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
