package org.tc.gateway.core.ports.external.database;

import org.tc.infrastructure.postgres.entities.ApiRouteEntity;

import java.util.List;

public interface GetAllRoutesDatabaseQuery {
    List<ApiRouteEntity> getAllRoutes();
}
