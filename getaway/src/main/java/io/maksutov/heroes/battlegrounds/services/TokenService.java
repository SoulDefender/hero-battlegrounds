package io.maksutov.heroes.battlegrounds.services;

import io.maksutov.heroes.battlegrounds.model.CustomToken;

import java.util.Optional;

public interface TokenService {

    void save(CustomToken token);

    Optional<CustomToken> find(String token);

}
