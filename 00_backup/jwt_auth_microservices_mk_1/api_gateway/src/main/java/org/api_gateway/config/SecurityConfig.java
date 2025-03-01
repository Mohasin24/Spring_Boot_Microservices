package org.api_gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){

        logger.info("Inside the SecurityConfig api gateway");

        httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeExchange(
                    exchange->exchange
                            .pathMatchers("/test","/api/v1/auth/register","/api/v1/auth/login")
                            .permitAll()
                            .anyExchange()
                            .authenticated()
                );

        return httpSecurity.build();
    }
}
