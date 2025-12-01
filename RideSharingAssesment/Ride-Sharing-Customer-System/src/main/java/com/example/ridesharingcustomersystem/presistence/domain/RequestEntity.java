package com.example.ridesharingcustomersystem.presistence.domain;

import com.example.ridesharingcustomersystem.business.enums.RideRequestState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="Request")
@Getter
@Setter
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "DRIVER_ID")
    private Long driverId;

    @Column(name = "DRIVER_Name")
    private String driverName;

    @Column(name = "REQUEST_DATE_TIME")
    private LocalDateTime requestDateTime;

    @Column(name = "PICKUP_LAT")
    private Double pickupLat;

    @Column(name = "PICKUP_LNG")
    private Double pickupLng;

    @Column(name = "DROPOFF_LAT")
    private Double dropoffLat;

    @Column(name = "DROPOFF_LNG")
    private Double dropoffLng;

    @Column(name = "REQUEST_STATE")
    private Integer requestState;

    @Transient
    private String requestStateDesc;

    @PostLoad
    public void postLoad(){
        this.requestStateDesc= RideRequestState.fromCode(requestState).getDescription();
    }

}
