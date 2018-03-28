package io.maksutov.heroes.battlegrounds.security.auth.jwt.extractor;

public interface TokenExtractor {

    String extract(String payload);

}
