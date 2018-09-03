package io.maksutov.heroes.battlegrounds.user.repository.entities;

import io.maksutov.heroes.battlegrounds.model.user.Role;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "USER_ROLE", catalog = "user_storage")
public class UserRole {

    @Embeddable
    public static class Id implements Serializable {

        private static final long serialVersionUID = 1322120000551624359L;

        @Column(name = "APP_USER_ID") Long userId;

        @Enumerated(EnumType.STRING)
        @Column(name = "ROLE") Role role;


        Id() {

        }


        public Id(Long userId, Role role) {

            this.userId = userId;
            this.role = role;
        }
    }


    @EmbeddedId
    Id id = new Id();

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", insertable = false, updatable = false)
    private Role role;


    public Role getRole() {

        return role;
    }
}
