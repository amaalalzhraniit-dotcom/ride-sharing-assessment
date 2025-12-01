package com.example.ridesharingsystem.business.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RideRequestDto {

    private LocalDateTime requestDateTime;

    private Double pickupLat;

    private Double pickupLng;

    private Double dropoffLat;

    private Double dropoffLng;
}
