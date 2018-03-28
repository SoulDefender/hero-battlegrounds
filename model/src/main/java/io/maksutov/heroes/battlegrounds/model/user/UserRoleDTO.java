package io.maksutov.heroes.battlegrounds.model.user;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserRoleDTO {

    Id id;

    protected Role role;


    public static class Id implements Serializable {

        private static final long serialVersionUID = 1322120000551624359L;

        protected Long userId;

        protected Role role;


        public Id() {

        }


        public Id(Long userId, Role role) {

            this.userId = userId;
            this.role = role;
        }
    }

}
