package org.api_gateway.filter;

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

import static org.api_gateway.constants.Paths.API_PATHS;

@Component
@Order(0)
public class JwtAuthenticationFilter  implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService){

        this.jwtService=jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("JwtAuthenticationFilter triggered for URI: {}", exchange.getRequest().getURI());

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

//        System.err.println(exchange.getRequest().getHeaders());
//
//
//        System.err.println(exchange.getRequest().getPath().toString());
//        System.err.println(API_PATHS.contains(exchange.getRequest().getPath().toString()));

//        //////////// Checking the request
        String request = exchange.getRequest().getPath().toString();

        if(request.equals("/test") || request.equals("/demo")){
            return chain.filter(exchange);
        }

        if(API_PATHS.contains(request)){
            return chain.filter(exchange);
        }

//        //////////// END


//        if( !token.startsWith("Bearer ")){
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }

        token = token.substring(7);
        System.err.println("Token:" + token);
        if(!jwtService.isTokenValid(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}
