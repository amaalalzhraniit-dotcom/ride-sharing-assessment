package com.example.ridesharingsystem.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RideHistoryResponseDto {

    @JsonProperty("requestId")
    private Long id;

    private String driverName;

    private LocalDateTime requestDateTime;

    private Double pickupLat;

    private Double pickupLng;

    private Double dropoffLat;

    private Double dropoffLng;

    private String requestStateDesc;

}
