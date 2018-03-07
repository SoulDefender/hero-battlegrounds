package com.epam.microservice.sandbox.microservicesandbox.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class User implements Serializable {

    private String name;
    private String password;
    private List<Role> roles;

    public User(String name, String password, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }
}
