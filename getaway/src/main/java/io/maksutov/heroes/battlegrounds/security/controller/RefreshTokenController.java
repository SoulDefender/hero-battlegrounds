package io.maksutov.heroes.battlegrounds.security.controller;

import io.maksutov.heroes.battlegrounds.model.user.UserDTO;
import io.maksutov.heroes.battlegrounds.security.auth.jwt.extractor.TokenExtractor;
import io.maksutov.heroes.battlegrounds.security.auth.jwt.verifier.TokenVerifier;
import io.maksutov.heroes.battlegrounds.security.config.JwtSettings;
import io.maksutov.heroes.battlegrounds.security.config.WebSecurityConfig;
import io.maksutov.heroes.battlegrounds.security.exceptions.InvalidJwtToken;
import io.maksutov.heroes.battlegrounds.security.model.UserContext;
import io.maksutov.heroes.battlegrounds.security.model.token.JwtToken;
import io.maksutov.heroes.battlegrounds.security.model.token.JwtTokenFactory;
import io.maksutov.heroes.battlegrounds.security.model.token.RawAccessJwtToken;
import io.maksutov.heroes.battlegrounds.security.model.token.RefreshToken;
import io.maksutov.heroes.battlegrounds.service.UserInfoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class RefreshTokenController {

    private final JwtTokenFactory tokenFactory;
    private final JwtSettings jwtSettings;
    private final UserInfoService userInfo;
    private final TokenVerifier tokenVerifier;
    private final TokenExtractor tokenExtractor;


    public RefreshTokenController(JwtTokenFactory tokenFactory, JwtSettings jwtSettings, UserInfoService userInfo,
            TokenVerifier tokenVerifier, @Qualifier("jwtHeaderTokenExtractor") TokenExtractor tokenExtractor) {

        this.tokenFactory = tokenFactory;
        this.jwtSettings = jwtSettings;
        this.userInfo = userInfo;
        this.tokenVerifier = tokenVerifier;
        this.tokenExtractor = tokenExtractor;
    }


    @RequestMapping(value = "/api/auth/token", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request) {

        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME));

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey())
                .orElseThrow(InvalidJwtToken::new);

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        UserDTO user = userInfo.getByUsername(subject)
                .orElseThrow(() -> new UsernameNotFoundException("UserDTO not found: " + subject));

        if (user.getRoles() == null)
            throw new InsufficientAuthenticationException("UserDTO has no roles assigned");
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }

}
