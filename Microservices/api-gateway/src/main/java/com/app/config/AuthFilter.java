package com.app.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class AuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Extract token from request header
        String token =exchange.getRequest().getHeaders().getFirst("Authorization");

        // validating token
        if(Objects.isNull(token) || !validToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Forward request, If token is valid
        return chain.filter(exchange);
    }

    public boolean validToken(String token) {
        return "Bearer valid-token".equals(token);
    }
}
