package com.example.ridesharingcustomersystem.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RideRequestState {

    NEW(0, "New request, waiting for driver"),
    DRIVER_ASSIGNED(1, "Driver assigned to request"),
    IN_PROGRESS(2, "Ride is currently in progress"),
    COMPLETED(3, "Ride completed successfully"),
    CANCELED(4, "Ride canceled by customer or driver");

    private final int code;
    private final String description;

    public static RideRequestState fromCode(int code) {
        for (RideRequestState state : values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid RideRequestState code: " + code);
    }
}