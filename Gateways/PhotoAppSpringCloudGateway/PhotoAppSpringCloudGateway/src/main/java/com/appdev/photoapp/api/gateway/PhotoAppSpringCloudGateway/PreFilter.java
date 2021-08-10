package com.appdev.photoapp.api.gateway.PhotoAppSpringCloudGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class PreFilter implements GlobalFilter {
    final Logger logger= LoggerFactory.getLogger(PreFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("My First Pre Filter");
        String requestPath = exchange.getRequest().getPath().toString();
        logger.info("Request Path :" + requestPath);

        Set<String> headerNames = exchange.getRequest().getHeaders().keySet();
        headerNames.forEach((headerName) ->
        {
            String headerValue=exchange.getRequest().getHeaders().getFirst(headerName);
            logger.info(headerName+"--->"+ headerValue);
        });


        return chain.filter(exchange);
    }
}
