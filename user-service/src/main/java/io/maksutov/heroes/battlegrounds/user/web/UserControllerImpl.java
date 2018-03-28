package io.maksutov.heroes.battlegrounds.user.web;

import io.maksutov.heroes.battlegrounds.model.user.UserDTO;
import io.maksutov.heroes.battlegrounds.user.repository.entities.User;
import io.maksutov.heroes.battlegrounds.user.web.service.UserService;
import io.maksutov.heroes.battlegrounds.web.UserController;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Dmytro_Maksutov
 */
@RestController
public class UserControllerImpl implements UserController {

    private final ModelMapper mapper;
    private UserService userService;


    public UserControllerImpl(UserService userService, ModelMapper mapper) {

        this.userService = userService;
        this.mapper = mapper;
    }


    @Override
    public UserDTO getByUsername(@PathVariable("username") String username) {

        User user = userService.findUserByName(username);
        return mapper.map(user, UserDTO.class);
    }


    @Override public UserDTO addNewUser(@RequestBody UserDTO userDTO) {

        User user = mapper.map(userDTO, User.class);
        user = userService.saveUser(user);
        return mapper.map(user, UserDTO.class);
    }
}
