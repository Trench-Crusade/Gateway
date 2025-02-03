package org.tc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tc.gateway.core.usecases.FindAllRoutesUseCase;
import org.tc.gateway.core.utility.ApiRouteLocator;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator routeLocator(FindAllRoutesUseCase findAllRoutesUseCase,
                                     RouteLocatorBuilder routeLocationBuilder) {
        return new ApiRouteLocator(routeLocationBuilder, findAllRoutesUseCase);
    }
}
