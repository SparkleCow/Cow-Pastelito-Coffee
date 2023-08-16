package com.cow.pastelitocoffe.cow.pastelitocoffe.Security.SecurityConfig;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Security.Jwt.JwtAuthorityEntryPoint;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Security.Jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public JwtAuthorityEntryPoint jwtAuthorityEntryPoint;
    public SecurityConfig(JwtAuthorityEntryPoint jwtAuthorityEntryPoint) {
        this.jwtAuthorityEntryPoint = jwtAuthorityEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter jwtAuthenticationFilter() {
        return new JwtFilter();
    }

    /*Spring Security Configuration*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                .csrf(AbstractHttpConfigurer::disable).cors(withDefaults())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthorityEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/calculateCoffee/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/coffees/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/coffees", "/api/users/createUser", "/api/users/createEmployee").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/coffees/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/coffees/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users").hasAuthority("ADMIN")
                        .requestMatchers("/api/ingredients/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();
    }
}
