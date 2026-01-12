package config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class gatewayConfig {

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder ) {
//        return builder.routes()
//            .route("get-users", r -> r
//                .path("/api/v1/users")
//                .filters(f -> f.rewritePath(
//                        "/users/(?<segment>.*)",
//                        "/api/v1/users/${segment}"
//                ))
//                .uri("http://localhost:8080")
//            )
//            .build();
//    }

}
