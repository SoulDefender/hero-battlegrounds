package com.epam.microservice.sandbox.microservicesandbox.services;

import com.epam.microservice.sandbox.microservicesandbox.model.CustomToken;

import java.util.Optional;

public interface TokenService {

    void save(CustomToken token);

    Optional<CustomToken> find(String token);

}
