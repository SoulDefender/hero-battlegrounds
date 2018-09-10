package io.maksutov.heroes.battlegrounds.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.maksutov.heroes.battlegrounds.security.RestAuthEntryPoint;
import io.maksutov.heroes.battlegrounds.security.auth.ajax.AjaxAuthProvider;
import io.maksutov.heroes.battlegrounds.security.auth.ajax.AjaxLoginProcessingFilter;
import io.maksutov.heroes.battlegrounds.security.auth.jwt.JwtAuthenticationProvider;
import io.maksutov.heroes.battlegrounds.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import io.maksutov.heroes.battlegrounds.security.auth.jwt.SkipPathRequestMatcher;
import io.maksutov.heroes.battlegrounds.security.auth.jwt.extractor.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static javax.ws.rs.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    private static final String AUTHENTICATION_URL = "/api/auth/login";
    private static final String REFRESH_TOKEN_URL = "/api/auth/token";
    private static final String API_HEROES = "/api/heroes/**";

    private final RestAuthEntryPoint authenticationEntryPoint;
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final AjaxAuthProvider ajaxAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final TokenExtractor tokenExtractor;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final ObjectMapper objectMapper;


    public WebSecurityConfig(RestAuthEntryPoint authenticationEntryPoint, AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler, AjaxAuthProvider ajaxAuthenticationProvider,
            JwtAuthenticationProvider jwtAuthenticationProvider, TokenExtractor tokenExtractor,
            ObjectMapper objectMapper) {

        this.authenticationEntryPoint = authenticationEntryPoint;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.ajaxAuthenticationProvider = ajaxAuthenticationProvider;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.tokenExtractor = tokenExtractor;
        this.objectMapper = objectMapper;
    }


    private AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String loginEntryPoint) {

        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(loginEntryPoint, successHandler, failureHandler,
                objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }


    private JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter(
            List<String> pathsToSkip, String pattern) {

        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, pattern,
                POST, HttpMethod.PUT, HttpMethod.DELETE);
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        List<String> permitAllEndpointList = Arrays.asList(
                AUTHENTICATION_URL,
                REFRESH_TOKEN_URL,
                "/console",
                "/api/**"
        );

        http
                .cors().and()
                .csrf().disable() // We don't need CSRF for JWT based authentication
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(GET, API_HEROES).permitAll()
                .antMatchers(POST, API_HEROES).authenticated()
                .antMatchers(PUT, API_HEROES).authenticated()
                .antMatchers(permitAllEndpointList.toArray(new String[] {})).permitAll()
                .and()
                .addFilterBefore(buildAjaxLoginProcessingFilter(AUTHENTICATION_URL),
                        FilterSecurityInterceptor.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(Arrays.asList(
                        AUTHENTICATION_URL,
                        REFRESH_TOKEN_URL,
                        "/console"),
                        API_HEROES),
                        FilterSecurityInterceptor.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "HEAD"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
