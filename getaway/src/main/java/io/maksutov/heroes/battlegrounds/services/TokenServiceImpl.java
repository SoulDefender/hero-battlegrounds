package io.maksutov.heroes.battlegrounds.services;

import io.maksutov.heroes.battlegrounds.model.CustomToken;
import io.maksutov.heroes.battlegrounds.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void save(CustomToken token) {
        this.tokenRepository.save(token);
    }

    @Override
    public Optional<CustomToken> find(String token) {
        return Optional.of(this.tokenRepository.findOne(token));
    }
}
