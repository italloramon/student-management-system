package com.ramon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Admin implements User {
    @Id
    @GeneratedValue
    private Long id;

    @Override
    public String getUsername() {
        return "admin";
    }

    @Override
    public String getPassword() {
        return "admin";
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }

    @Override
    public Long getId() {
        return null;
    }
}
