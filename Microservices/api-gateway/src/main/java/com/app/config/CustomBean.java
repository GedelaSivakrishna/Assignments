package com.app.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomBean {

    @Bean
    public GlobalFilter customFilter() {
        return (exchange, chain) -> {
            System.out.println("Request passed through API Gateway: " + exchange.getRequest().getPath());
            return chain.filter(exchange);
        };
    }

    @Bean
    public GlobalFilter customRoutingFilter() {
        return (exchange, chain) -> {
            System.out.println("Routing request to: " + exchange.getRequest().getURI());
            return chain.filter(exchange);
        };
    }
}
