package com.kumaral.gateway.springcloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteBeanConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/car/**")
                        .filters(f -> f.addRequestHeader("model", "baleno")
                                .addResponseHeader("company", "maruti")
                                .hystrix(h -> h.setName("Hystrix")
                                        .setFallbackUri("forward:/fallback/msg")))
                        .uri("lb://car-service")
                        .id("carService"))

                .route(r -> r.path("/gateway/**")
                        .filters(f -> f.hystrix(h -> h.setName("Hystrix")
                                        .setFallbackUri("forward:/fallback/msg")))
                        .uri("lb://api-gateway")
                        .id("apiGateway"))
                .build();
    }
}
