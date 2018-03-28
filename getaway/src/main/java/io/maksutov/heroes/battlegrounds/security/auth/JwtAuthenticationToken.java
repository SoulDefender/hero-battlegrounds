package io.maksutov.heroes.battlegrounds.security.auth;

import io.maksutov.heroes.battlegrounds.security.model.UserContext;
import io.maksutov.heroes.battlegrounds.security.model.token.RawAccessJwtToken;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


/**
 * An {@link org.springframework.security.core.Authentication} implementation
 * that is designed for simple presentation of JwtToken.
 *
 * @author vladimir.stankovic
 * <p>
 * May 23, 2016
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 2877954820905567501L;

    private RawAccessJwtToken rawAccessToken;
    private UserContext userContext;


    public JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {

        super(null);
        this.rawAccessToken = unsafeToken;
        this.setAuthenticated(false);
    }


    public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {

        super(authorities);
        this.eraseCredentials();
        this.userContext = userContext;
        super.setAuthenticated(true);
    }


    @Override
    public void setAuthenticated(boolean authenticated) {

        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }


    @Override
    public Object getCredentials() {

        return rawAccessToken;
    }


    @Override
    public Object getPrincipal() {

        return this.userContext;
    }


    @Override
    public void eraseCredentials() {

        super.eraseCredentials();
        this.rawAccessToken = null;
    }


    @Override public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof JwtAuthenticationToken))
            return false;

        JwtAuthenticationToken that = (JwtAuthenticationToken) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(rawAccessToken, that.rawAccessToken)
                .append(userContext, that.userContext)
                .isEquals();
    }


    @Override public int hashCode() {

        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(rawAccessToken)
                .append(userContext)
                .toHashCode();
    }
}
