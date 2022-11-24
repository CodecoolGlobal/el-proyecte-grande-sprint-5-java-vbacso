package com.codecool.byteMe.security;

import com.codecool.byteMe.service.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AppUserDetailService userDetailService;
    private final SecretKey secretKey;
    private final JwtConfiguration jwtConfiguration;

    @Autowired
    public AppWebSecurityConfiguration(PasswordEncoder passwordEncoder, AppUserDetailService userDetailService, SecretKey secretKey, JwtConfiguration jwtConfiguration) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
        this.secretKey = secretKey;
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtEmailAndPasswordAuthenticationFilter(authenticationManager(), jwtConfiguration, secretKey))
                .addFilterAfter(new JwtValidation(secretKey, jwtConfiguration), JwtEmailAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/login", "/registration").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setExposedHeaders(List.of("Authorization"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
