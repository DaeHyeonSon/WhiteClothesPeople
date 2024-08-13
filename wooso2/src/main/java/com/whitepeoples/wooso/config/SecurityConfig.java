package com.whitepeoples.wooso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/**", "/login", "/home", "/public/**").permitAll() // 이 경로들은 인증 없이 접근 가능
                                .anyRequest().authenticated() // 다른 모든 요청은 인증 필요
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/login") // 커스텀 로그인 페이지 경로
                                .defaultSuccessUrl("/home") // 로그인 성공 시 이동할 경로
                                .failureUrl("/login?error=true") // 로그인 실패 시 이동할 경로
                );

        return http.build();
    }
}


