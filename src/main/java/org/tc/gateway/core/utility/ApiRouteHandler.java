package org.tc.gateway.core.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.tc.gateway.core.usecases.AddRouteUseCase;
import org.tc.gateway.core.usecases.FindRouteUseCase;
import org.tc.infrastructure.postgres.entities.ApiRouteEntity;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@RequiredArgsConstructor
@Component
public class ApiRouteHandler {
    private final AddRouteUseCase addRouteUseCase;
    private final FindRouteUseCase findRouteUseCase;

    private final GatewayRoutesRefresher gatewayRoutesRefresher;

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Mono<ApiRouteEntity> apiRoute = serverRequest.bodyToMono(ApiRouteEntity.class);
        return apiRoute.flatMap(route ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(addRouteUseCase.save(route), ApiRouteEntity.class));
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        final String apiId = serverRequest.pathVariable("routeId");
        Optional<ApiRouteEntity> apiRoute = findRouteUseCase.findRouteById(Long.valueOf(apiId));
        if(apiRoute.isEmpty()){
            return ServerResponse.notFound().build();
        }
        else{
            return apiRoute.map(route -> ServerResponse.ok()
                    .body(fromValue(route))).get();
        }
    }
    public Mono<ServerResponse> refreshRoutes() {
        gatewayRoutesRefresher.refreshRoutes();
        return ServerResponse.ok().body(BodyInserters.fromValue("Routes reloaded successfully"));
    }
}