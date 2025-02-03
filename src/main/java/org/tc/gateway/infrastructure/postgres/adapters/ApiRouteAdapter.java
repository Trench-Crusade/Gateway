package org.tc.gateway.infrastructure.postgres.adapters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tc.gateway.infrastructure.postgres.repositories.ApiRouteRepository;
import org.tc.gateway.core.ports.external.database.GetAllRoutesDatabaseQuery;
import org.tc.gateway.core.ports.external.database.GetRouteByIdDatabaseQuery;
import org.tc.gateway.core.ports.external.database.SaveRouteDatabaseCommand;
import org.tc.infrastructure.postgres.entities.ApiRouteEntity;

import java.util.List;
import java.util.Optional;

@Service
public class ApiRouteAdapter implements GetAllRoutesDatabaseQuery, GetRouteByIdDatabaseQuery, SaveRouteDatabaseCommand {

    private ApiRouteRepository apiRouteRepository;

    @Value("${tc.authservice.uri}")
    private String authServiceUri;

    public ApiRouteAdapter(ApiRouteRepository apiRouteRepository) {
        this.apiRouteRepository = apiRouteRepository;
    }

    @Override
    public List<ApiRouteEntity> getAllRoutes() {
        List<ApiRouteEntity> apiRouteEntityList = apiRouteRepository.findAll();
        apiRouteEntityList.forEach(route -> {
            switch(route.getUri()){
                case "tc.authservice.uri" -> route.setUri(authServiceUri);
            }
        });
        return apiRouteEntityList;
    }

    @Override
    public Optional<ApiRouteEntity> getById(Long id) {
        return apiRouteRepository.findById(id);
    }

    @Override
    public ApiRouteEntity save(ApiRouteEntity apiRouteEntity) {
        return apiRouteRepository.save(apiRouteEntity);
    }
}
