package io.maksutov.heroes.battlegrounds.service;

import io.maksutov.heroes.battlegrounds.model.user.UserDTO;
import io.maksutov.heroes.battlegrounds.remote.UserInfoController;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserInfoService {

    private final UserInfoController userInfoController;


    public UserInfoService(UserInfoController userInfoController) {

        this.userInfoController = userInfoController;
    }


    public Optional<UserDTO> getByUsername(String username) {

        return Optional.ofNullable(userInfoController.getByUsername(username));
    }

}
