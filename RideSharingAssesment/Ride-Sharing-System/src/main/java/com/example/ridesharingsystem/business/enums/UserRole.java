package com.example.ridesharingsystem.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    CUSTOMER(0, "CUSTOMER", "Customer who requests rides"),
    DRIVER(1, "DRIVER", "Driver who accepts rides");

    private final int id;
    private final String roleName;
    private final String description;

    public static UserRole fromId(int id) {
        for (UserRole r : values()) {
            if (r.id == id) return r;
        }
        throw new IllegalArgumentException("Invalid UserRole id: " + id);
    }
}
