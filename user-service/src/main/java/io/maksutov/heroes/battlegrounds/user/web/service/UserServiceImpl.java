package io.maksutov.heroes.battlegrounds.user.web.service;

import io.maksutov.heroes.battlegrounds.user.repository.entities.User;
import io.maksutov.heroes.battlegrounds.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Dmytro_Maksutov
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override public User findUserByName(String name) {

        return userRepository.findUserByUsername(name);
    }


    @Override @Transactional public User saveUser(User user) {

        return userRepository.save(user);
    }
}
