package com.app.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    // Route handling for all microservices
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("employee-service", r -> r.path("/employee/**")
                        .filters(f -> f
                                        .requestRateLimiter().configure(c -> c.setRateLimiter(redisRateLimiter()))
                                        .circuitBreaker(c -> c
                                        .setName("employeeServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/employee")))
                                .uri("lb://EMPLOYEE-SERVICE"))

                .route("performance-service", r -> r.path("/performance/**")
                        .filters(f -> f
                                        .requestRateLimiter().configure(c -> c.setRateLimiter(redisRateLimiter()))
                                        .circuitBreaker(c -> c
                                        .setName("performanceServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/performance")))
                                .uri("lb://PERFORMANCE-SERVICE"))

                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        // RedisRateLimiter uses token Algorithm to manage rating
        // ReplenishRate - the number of tokens refills per sec
        // Burst Rate - 2 tokens can be spent in 1 sec, i.e 2 requests can be processed in 1 sec
        return new RedisRateLimiter(1, 2);
    }

    @Bean
    public KeyResolver keyResolver() {
        // Client Ip address is used to uniquely identify each request
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
