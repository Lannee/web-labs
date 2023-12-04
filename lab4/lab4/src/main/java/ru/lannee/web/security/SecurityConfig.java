package ru.lannee.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.lannee.web.security.jwt.AuthEntryPoint;
import ru.lannee.web.security.jwt.AuthTokenFilter;
import ru.lannee.web.security.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private String[] AUTH_WHITELIST = new String[]{"/api/auth/register", "/api/auth/login", "/api/controller"};

    private final UserService userService;

    private final AuthEntryPoint unauthorizedHandler;

    @Autowired
    public SecurityConfig(UserService userService, AuthEntryPoint unauthorizedHandler) {
        this.userService = userService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
//                        .anyRequest().authenticated());
//
//        http.authenticationProvider(authenticationProvider())
//                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();

        return http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req -> {
//            req.requestMatchers(AUTH_WHITELIST).permitAll();
//            req.requestMatchers( * AUTH_LIST).authenticated();
//            req.requestMatchers("/**").denyAll();
            req.requestMatchers("/**").permitAll();
        })
        .sessionManagement(strategy -> strategy.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        .build();

    }
}