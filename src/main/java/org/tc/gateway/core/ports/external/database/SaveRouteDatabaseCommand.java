package org.tc.gateway.core.ports.external.database;

import org.tc.infrastructure.postgres.entities.ApiRouteEntity;

public interface SaveRouteDatabaseCommand {
    ApiRouteEntity save(ApiRouteEntity apiRouteEntity);
}
