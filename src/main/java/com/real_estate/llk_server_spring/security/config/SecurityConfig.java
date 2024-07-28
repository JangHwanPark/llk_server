package com.real_estate.llk_server_spring.security.config;

import com.real_estate.llk_server_spring.security.detail.CustomOauth2UserDetailService;
import com.real_estate.llk_server_spring.security.entity.RefreshRepository;
import com.real_estate.llk_server_spring.security.filter.CustomLogoutFilter;
import com.real_estate.llk_server_spring.security.filter.JWTFilter;
import com.real_estate.llk_server_spring.security.filter.LoginFilter;
import com.real_estate.llk_server_spring.security.handler.CustomSuccessHandler;
import com.real_estate.llk_server_spring.security.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil util;
    private final RefreshRepository refreshRepository;
    private final CustomOauth2UserDetailService customOauth2UserDetailService;
    private final CustomSuccessHandler customSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.formLogin((formLogin) -> formLogin.disable());
        http.httpBasic((httpBasic) -> httpBasic.disable());
        http.authorizeHttpRequests((req)->
                    req
                            .requestMatchers("/join","/reissue","/products/**","/availability/email",
                                    "/contact","/review/list","/region/state/list",
                                    "/products/list").permitAll()
                            .requestMatchers("/review/add").hasAnyRole("USER","ADMIN","AGENT")
                            .requestMatchers("/admin/user/**", "/products/add","/products/delete/**").hasRole("ADMIN")
                            .requestMatchers("/region/state/add","/region/state/delete", "/region/state/update").hasAnyRole("ADMIN","AGENT")
                            .anyRequest().authenticated()
                );
        http.cors((cors) ->
                    cors.configurationSource(new CorsConfigurationSource() {
                        @Override
                        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowCredentials(true);
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setMaxAge(3600L);
                            config.setExposedHeaders(Arrays.asList("Access","Set-Cookie"));
                            return config;
                        }
                    })
                );
        http.sessionManagement((session) ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.addFilterBefore(new JWTFilter(util), LoginFilter.class);
        http.addFilterBefore(new CustomLogoutFilter(util,refreshRepository), LogoutFilter.class);
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),util,refreshRepository), UsernamePasswordAuthenticationFilter.class);
        http.oauth2Login((oauth2) ->
                oauth2.userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig.userService(customOauth2UserDetailService))
                        .successHandler(customSuccessHandler));
        return http.build();
    }

}