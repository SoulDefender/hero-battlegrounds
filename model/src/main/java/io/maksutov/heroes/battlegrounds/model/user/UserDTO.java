package io.maksutov.heroes.battlegrounds.model.user;

import lombok.Data;

import java.util.List;


@Data
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private List<UserRoleDTO> roles;

}