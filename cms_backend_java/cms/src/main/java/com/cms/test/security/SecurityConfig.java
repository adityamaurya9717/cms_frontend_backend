package com.cms.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthEntryPoint authEntryPoint;
    private final CustomUserDetailServiceImpl customUserDetailService;

    @Autowired
    public SecurityConfig(JwtAuthEntryPoint jwtAuthEntryPoint, CustomUserDetailServiceImpl customUserDetailService) {
        this.authEntryPoint = jwtAuthEntryPoint;
        this.customUserDetailService = customUserDetailService;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String allowPath[] = {"/api/public/ping","/api/customer/**"};
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //.antMatchers(allowPath).permitAll() // access these API with out token
                 //.anyRequest().authenticated() // other than this token is validated
                .anyRequest().permitAll()
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
        //return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public  JwtFilter jwtAuthenticationFilter() {
        return new JwtFilter();
    }
}
