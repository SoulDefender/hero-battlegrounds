package com.epam.microservice.sandbox.microservicesandbox.config;

import com.epam.microservice.sandbox.microservicesandbox.model.CustomToken;
import com.epam.microservice.sandbox.microservicesandbox.repository.TokenRepository;
import com.epam.microservice.sandbox.microservicesandbox.services.TokenService;
import com.epam.microservice.sandbox.microservicesandbox.utils.Constants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class TokenAuthenticationFilter extends GenericFilterBean {

    private final TokenService tokenService;

    public TokenAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;

        //extract token from header
        final String accessToken = httpRequest.getHeader(Constants.AUTH_TOKEN_HEADER);
        if (null != accessToken) {
            //get and check whether token is valid ( from DB or file wherever you are storing the token)
            Optional<CustomToken> customToken = tokenService.find(accessToken);
            customToken.ifPresent(token -> {
                //Populate SecurityContextHolder by fetching relevant information using token
                final User user = new User(
                        token.getUser().getName(),
                        token.getUser().getPassword(),
                        true,
                        true,
                        true,
                        true,
                        token.getUser().getRoles().stream().map(r -> new SimpleGrantedAuthority(
                                r.toString())).collect(Collectors.toList()));
                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }

        chain.doFilter(request, response);
    }

}