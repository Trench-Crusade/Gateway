package org.tc.gateway.core.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tc.gateway.core.ports.external.database.SaveRouteDatabaseCommand;
import org.tc.infrastructure.postgres.entities.ApiRouteEntity;

@Service
@AllArgsConstructor
public class AddRouteUseCase {
    private final SaveRouteDatabaseCommand saveRouteDatabaseCommand;

    public ApiRouteEntity save(ApiRouteEntity apiRouteEntity) {
        return saveRouteDatabaseCommand.save(apiRouteEntity);
    }

}
