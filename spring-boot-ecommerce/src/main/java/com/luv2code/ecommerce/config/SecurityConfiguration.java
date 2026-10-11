package com.luv2code.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Protect /api/orders/** endpoint
        http.authorizeHttpRequests(requests ->
                requests
                        .requestMatchers("/api/orders/**").authenticated()
                        .anyRequest().permitAll()
        );

        // Validate Okta JWT access tokens
        http.oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer.jwt(Customizer.withDefaults())
        );

        // Enable CORS
        http.cors(Customizer.withDefaults());

        // Content negotiation
        http.setSharedObject(
                ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy()
        );

        // We are not using cookies/session authentication
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
