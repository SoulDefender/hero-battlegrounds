package com.epam.microservice.sandbox.microservicesandbox.model;

import java.io.Serializable;

public enum Role implements Serializable {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    public static Role of(String roleName) {
        for (Role role: values()) {
            if (role.toString().equals(roleName)) {
                return role;
            }
        }
        return null;
    }
}
