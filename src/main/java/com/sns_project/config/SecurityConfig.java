package com.sns_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration // IoC
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    public  SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 이 경로에서 권한을 요청하겠다.
        http.authorizeHttpRequests()
                .requestMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated()
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/auth/signin")
                .defaultSuccessUrl("/");

        return http.build();
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(new AntPathRequestMatcher("/")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/user/**")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/posting/**")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/subscribe/**")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/comment/**")).authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/auth/signin")
                        .defaultSuccessUrl("/posting")
                );

        return http.build();
    }
}