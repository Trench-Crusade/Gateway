package org.tc.gateway.core.ports.external.database;

import org.tc.infrastructure.postgres.entities.ApiRouteEntity;

import java.util.Optional;

public interface GetRouteByIdDatabaseQuery {
    Optional<ApiRouteEntity> getById(Long id);
}
