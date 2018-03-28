package io.maksutov.heroes.battlegrounds.user.web.service;

import io.maksutov.heroes.battlegrounds.user.repository.entities.User;


/**
 * @author Dmytro_Maksutov
 */
public interface UserService {

    User findUserByName(String name);

    User saveUser(User user);
}
