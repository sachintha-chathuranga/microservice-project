package com.fourbit.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
      @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
      private String issuerLocation;
      @Bean
      public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
            serverHttpSecurity
                        .csrf(ServerHttpSecurity.CsrfSpec::disable)
                        .authorizeExchange(exchange -> exchange.pathMatchers("/eureka/**")
                                    .permitAll()
                                    .anyExchange()
                                    .authenticated())
                        .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));
            return serverHttpSecurity.build();
      }
      @Bean
	public ReactiveJwtDecoder jwtDecoder() throws Exception{
		return ReactiveJwtDecoders.fromIssuerLocation(issuerLocation);
	}
}
