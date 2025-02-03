package org.tc.gateway.core.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tc.gateway.core.ports.external.database.GetAllRoutesDatabaseQuery;
import org.tc.infrastructure.postgres.entities.ApiRouteEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllRoutesUseCase {
    private final GetAllRoutesDatabaseQuery getAllRoutesDatabaseQuery;

    public List<ApiRouteEntity> getAllRoutes() {
        return getAllRoutesDatabaseQuery.getAllRoutes();
    }
}
