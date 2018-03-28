package io.maksutov.heroes.battlegrounds.security.auth.jwt.verifier;

public interface TokenVerifier {

    boolean verify(String jti);
}
