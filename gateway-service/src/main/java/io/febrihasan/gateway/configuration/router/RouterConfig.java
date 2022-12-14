package io.febrihasan.gateway.configuration.router;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author febrihasan
 */
@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/admin/**")
                        .uri("lb://admin-service"))
                .route(p -> p
                        .path("/api/config/**")
                        .uri("lb://config-service"))
                .route(p -> p
                        .path("/api/keycloak/**")
                        .uri("lb://keycloak-service"))
                .route(p -> p
                        .path("/api/notification/**")
                        .uri("lb://notification-service"))
                .route(p -> p
                        .path("/api/report/**")
                        .uri("lb://report-service"))
                .route(p -> p
                        .path("/api/user/**")
                        .uri("lb://user-service"))
                .route(p -> p
                        .path("/api/transaction/**")
                        .uri("lb://transaction-service"))
                .build();
    }

}
