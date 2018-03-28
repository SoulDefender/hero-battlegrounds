package io.maksutov.heroes.battlegrounds.security.auth.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.maksutov.heroes.battlegrounds.common.ErrorCode;
import io.maksutov.heroes.battlegrounds.common.ErrorResponse;
import io.maksutov.heroes.battlegrounds.security.exceptions.AuthMethodNotSupportedException;
import io.maksutov.heroes.battlegrounds.security.exceptions.JwtExpiredTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AjaxAwareAuthFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;


    public AjaxAwareAuthFailureHandler(ObjectMapper mapper) {

        this.mapper = mapper;
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException e) throws IOException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (e instanceof BadCredentialsException) {
            mapper.writeValue(response.getWriter(), ErrorResponse
                    .of("Invalid username or password", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        }
        else if (e instanceof JwtExpiredTokenException) {
            mapper.writeValue(response.getWriter(),
                    ErrorResponse.of("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
        }
        else if (e instanceof AuthMethodNotSupportedException) {
            mapper.writeValue(response.getWriter(),
                    ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        }

        mapper.writeValue(response.getWriter(),
                ErrorResponse.of("Authentication failed", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
    }
}