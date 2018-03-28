package io.maksutov.heroes.battlegrounds.web;

import io.maksutov.heroes.battlegrounds.model.user.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


public interface UserController {

    @GetMapping(value = "/users/{username}", produces = APPLICATION_JSON_VALUE)
    UserDTO getByUsername(@PathVariable("username") String username);

    @PostMapping(value = "users", consumes = APPLICATION_JSON_VALUE)
    UserDTO addNewUser(@RequestBody UserDTO user);

}
