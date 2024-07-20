package com.jarmison.dev.api.core.mapper;

import com.jarmison.dev.api.core.entity.AbstractEntity;

public interface MapperToEntity<E extends AbstractEntity> {
    E toEntity();
}