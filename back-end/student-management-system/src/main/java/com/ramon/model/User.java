package com.ramon.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

public interface User {
    public String getUsername();

    public String getPassword();

    public Role getRole();

    public Long getId();
}
