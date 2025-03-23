package com.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

// keycloak docker container installation prior to running this
// docker run -d -p 7080:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.1.4 start-dev
// converting gateway server to oauth2 resource servers


@Configuration
@EnableWebFluxSecurity    // spring cloud gateway is based on webflux that's why, otherwise @EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain2(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.authorizeExchange(exchanges ->
                        exchanges.pathMatchers(HttpMethod.GET).permitAll()
                                .pathMatchers("/eazybank/accounts/**").authenticated()
                                .pathMatchers("/eazybank/cards/**").authenticated()
                                .pathMatchers("/eazybank/loans/**").authenticated())
                // all get methods are allowed as parent is permitAll() and all other methods types will be authenticated
                // can use comma separated list instead of multiple pathMatchers ex -> .pathMatchers("/eazybank/accounts/**","/eazybank/cards/**","/eazybank/loans/**").authenticated()
                .oauth2ResourceServer(oAuth2ResourceServerSpec ->
                        oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()));   // default configuration
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);              // csrf disabled, only required when Frontend/browser is involved
        return serverHttpSecurity.build();
    }
}