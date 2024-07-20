package com.jarmison.dev.api.core.mapper;

import com.jarmison.dev.api.core.entity.AbstractEntity;
import java.util.List;

public interface MapperToResponse<R, D extends AbstractEntity> {
    R toResponse(D entity);
    List<R> toCollections(List<D> list);
}
