package com.example.ridesharingdriversystem.business.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DriverStatus {

    ONLINE(0, "Online"),
    OFFLINE(1, "Offline");

    private final int id;
    private final String description;

    public static DriverStatus fromId(int id) {
        for (DriverStatus status : values()) {
            if (status.id == id) return status;
        }
        throw new IllegalArgumentException("Invalid DriverStatus id: " + id);
    }
}
