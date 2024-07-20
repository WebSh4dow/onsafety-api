package com.jarmison.dev.api.core.mapper;

public interface MapperToDomain<D, E> {
    D toDomain(E request);
}
