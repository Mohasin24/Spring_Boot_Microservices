package org.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
        httpSecurity
                .csrf(csrfSpec -> csrfSpec.disable())
                .authorizeExchange(
                        exchange->exchange
                                .pathMatchers("/eureka/**")
                                .permitAll()
                                .anyExchange()
                                .authenticated()
                )
                .oauth2ResourceServer(outh2->outh2.jwt(
                        jwt -> jwt.jwkSetUri("http://localhost:8181/realms/spring-microservice-realm/protocol/openid-connect/certs")
                ));
                return httpSecurity.build();
    }
}
