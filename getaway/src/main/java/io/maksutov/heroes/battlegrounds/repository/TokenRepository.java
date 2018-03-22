package io.maksutov.heroes.battlegrounds.repository;

import io.maksutov.heroes.battlegrounds.model.CustomToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<CustomToken, String> {
}
