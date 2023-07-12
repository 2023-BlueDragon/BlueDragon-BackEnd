package com.bluedragon.arth.z_infrastructure.global.security.config;

import com.bluedragon.arth.z_infrastructure.global.jwt.filter.JwtExceptionFilter;
import com.bluedragon.arth.z_infrastructure.global.jwt.filter.JwtFilter;
import com.bluedragon.arth.z_infrastructure.global.jwt.utils.JwtParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtParser jwtParser;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()

                .and()
                .csrf().disable()
                .addFilterAfter(new JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(new ObjectMapper()), JwtFilter.class)

                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()

                //login, refresh
                .requestMatchers(POST, "/auth/**").permitAll()

                //signup user
                .requestMatchers(POST, "/user/**").permitAll()
                //query user
                .requestMatchers(GET, "/user/**").authenticated()

                //register question
                .requestMatchers(POST, "/question/**").hasRole("USER")
                //query questions
                .requestMatchers(GET, "/question/**").hasAnyRole("USER", "EXPERT")

                //register answer
                .requestMatchers(POST, "/answer/**").hasAnyRole("USER", "EXPERT")
                //query answers
                .requestMatchers(GET, "/answer/**").hasAnyRole("USER", "EXPERT")

                //upload files
                .requestMatchers("/file/**").hasAnyRole("USER", "EXPERT")

                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}