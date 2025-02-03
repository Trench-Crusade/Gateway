package org.tc.gateway.core.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tc.gateway.core.ports.external.database.GetRouteByIdDatabaseQuery;
import org.tc.infrastructure.postgres.entities.ApiRouteEntity;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindRouteUseCase {
    private final GetRouteByIdDatabaseQuery getRouteByIdDatabaseQuery;

    public Optional<ApiRouteEntity> findRouteById(Long id) {
        return getRouteByIdDatabaseQuery.getById(id);
    }
}
