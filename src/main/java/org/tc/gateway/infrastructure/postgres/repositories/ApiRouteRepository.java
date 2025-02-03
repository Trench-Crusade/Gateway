package org.tc.gateway.infrastructure.postgres.repositories;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tc.infrastructure.postgres.entities.ApiRouteEntity;

import java.util.List;

@Repository
public interface ApiRouteRepository extends CrudRepository<ApiRouteEntity, Long> {

    @NonNull
    @Override
    List<ApiRouteEntity> findAll();
}