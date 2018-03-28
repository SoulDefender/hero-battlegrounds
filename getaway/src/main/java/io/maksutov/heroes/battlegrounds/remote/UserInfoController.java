package io.maksutov.heroes.battlegrounds.remote;

import io.maksutov.heroes.battlegrounds.model.user.UserDTO;
import io.maksutov.heroes.battlegrounds.web.UserController;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(
        name = "user-service",
        url = "http://localhost:8095",
        fallback = UserInfoController.UserInfoControllerFallback.class
)
public interface UserInfoController extends UserController {

    class UserInfoControllerFallback implements UserInfoController {

        @Override public UserDTO getByUsername(String username) {

            return null;
        }


        @Override public UserDTO addNewUser(UserDTO user) {

            return null;
        }
    }

}
