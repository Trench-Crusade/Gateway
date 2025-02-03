package org.tc.gateway.core.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Service;
import org.tc.gateway.core.usecases.FindAllRoutesUseCase;
import org.tc.infrastructure.postgres.entities.ApiRouteEntity;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ApiRouteLocator implements RouteLocator {
    private final RouteLocatorBuilder routeLocatorBuilder;
    private final FindAllRoutesUseCase findAllRoutesUseCase;

    @Override
    public Flux<Route> getRoutes() {
        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
        List<RouteLocatorBuilder.Builder> builderList = findAllRoutesUseCase.getAllRoutes().stream()
                .map(apiRouteEntity -> routesBuilder.route(String.valueOf(apiRouteEntity.getRouteIdentifier()),
                        predicateSpec -> setPredicateSpec(apiRouteEntity, predicateSpec)))
                .toList();
        return Flux.fromIterable(builderList).transform(builders -> routesBuilder.build()
                        .getRoutes());
    }

    private Buildable<Route> setPredicateSpec(ApiRouteEntity apiRoute, PredicateSpec predicateSpec) {
        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getPath());
        if (    Objects.isNull(apiRoute.getMethod()) ||
                Objects.equals(" ",apiRoute.getMethod()) ||
                Objects.equals("",apiRoute.getMethod())
                ) {
            return booleanSpec.uri(apiRoute.getUri());
        }
        booleanSpec.and()
                .method(apiRoute.getMethod());
        return booleanSpec.uri(apiRoute.getUri());
    }

    @Override
    public Flux<Route> getRoutesByMetadata(Map<String, Object> metadata) {
        return RouteLocator.super.getRoutesByMetadata(metadata);
    }
}
