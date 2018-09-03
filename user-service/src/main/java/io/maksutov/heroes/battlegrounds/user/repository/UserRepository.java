package io.maksutov.heroes.battlegrounds.user.repository;

import io.maksutov.heroes.battlegrounds.user.repository.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String name);
}
