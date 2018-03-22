package io.maksutov.heroes.battlegrounds.config;

import io.maksutov.heroes.battlegrounds.model.CustomToken;
import io.maksutov.heroes.battlegrounds.model.Role;
import io.maksutov.heroes.battlegrounds.model.User;
import io.maksutov.heroes.battlegrounds.services.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.maksutov.heroes.battlegrounds.utils.Constants.AUTH_TOKEN_HEADER;


public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {


    private final TokenService tokenService;

    public CustomBasicAuthenticationFilter(final AuthenticationManager authenticationManager,
                                           final TokenService tokenService) {
        super(authenticationManager);
        this.tokenService = tokenService;
    }

    @Override
    protected void onSuccessfulAuthentication(final HttpServletRequest request,
                                              final HttpServletResponse response,
                                              final Authentication authResult) {
        //Generate Token
        String token = UUID.randomUUID().toString();
        //Save the token for the logged in user
        UserDetails principal = (UserDetails) authResult.getPrincipal();
        this.tokenService.save(new CustomToken(token, new User(principal.getUsername(),
                principal.getPassword(), principal.getAuthorities().stream().map(
                a -> Role.of(a.getAuthority())).collect(Collectors.toList())
        )));
        //send token in the response
        response.setHeader(AUTH_TOKEN_HEADER, token);
    }

}
