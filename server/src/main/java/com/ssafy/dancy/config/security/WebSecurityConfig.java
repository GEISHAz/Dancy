package com.ssafy.dancy.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(manage -> manage.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/start/test/userinfo").hasRole("USER")
                        .requestMatchers("/auth/logout").hasRole("USER")
                        .requestMatchers("/user/nickname").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,"/user/introduce").hasRole("USER")
                        .requestMatchers("/user/details").hasRole("USER")
                        .requestMatchers("/auth/change").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/auth").hasRole("USER")
                        .requestMatchers("/user/profile_image").hasRole("USER")
                        .requestMatchers("/stage").hasRole("USER")
                        .anyRequest().permitAll())
                .exceptionHandling(config ->
                        config.authenticationEntryPoint(authenticationEntryPoint()).
                                accessDeniedHandler(jwtAccessDeniedHandler))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }

    // 정적 자원에 대해서는 Security 설정을 하지 않음.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 허용할 CORS 부분에 설정하는 부분. (Security 에서도 반드시 필요하다)
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://localhost:8080", "http://localhost:3000"
        ));

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        ProviderManager manager = (ProviderManager) config.getAuthenticationManager();
        manager.setEraseCredentialsAfterAuthentication(false);
        return manager;
    }
}
