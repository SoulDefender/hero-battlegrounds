package com.epam.microservice.sandbox.microservicesandbox.repository;

import com.epam.microservice.sandbox.microservicesandbox.model.CustomToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<CustomToken, String> {
}
