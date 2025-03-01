package org.api_gateway.filter;

import org.api_gateway.constants.ApiPaths;
import org.api_gateway.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(0)
public class JwtFilter implements GlobalFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private ServerWebExchange exchange;

    private final JwtService jwtService;

    @Autowired
    public JwtFilter(JwtService jwtService){
        this.jwtService=jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getPath().toString();
        System.err.println(path);

        if(ApiPaths.ROUTES.contains(path)){
            System.err.println("Open Route: "+path);
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if(token==null || !token.startsWith("Bearer ")){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            System.err.println("Secured Authentication");
            return exchange.getResponse().setComplete();
        }

        token = token.substring(7);
        System.err.println(token);
        if(!jwtService.isTokenValid(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            System.err.println("Invalid token");
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
