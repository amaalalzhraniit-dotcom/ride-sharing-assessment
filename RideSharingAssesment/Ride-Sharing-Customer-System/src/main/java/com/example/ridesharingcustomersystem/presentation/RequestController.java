package com.example.ridesharingcustomersystem.presentation;

import com.example.ridesharingcustomersystem.business.dto.RideAssignDriverDto;
import com.example.ridesharingcustomersystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingcustomersystem.business.dto.RideRequestDto;
import com.example.ridesharingcustomersystem.business.service.RequestRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ride-request/")
@RequiredArgsConstructor
public class RequestController {
    private final RequestRideService requestRideService;

    @PostMapping("create")
    public ResponseEntity<Long> createRequest(@RequestHeader("X-User-Id") Long customerId , @RequestBody RideRequestDto rideRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(requestRideService.createRequest(customerId,rideRequestDto));
    }

    @GetMapping("request-history")
    public ResponseEntity<List<RideHistoryResponseDto>> getHistory(@RequestHeader("X-User-Id") Long customerId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(requestRideService.findAllByCustomerId(customerId));
    }

    @PutMapping("update")
    public ResponseEntity<Long> assignDriver(@RequestBody RideAssignDriverDto rideAssignDriverDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(requestRideService.assignDriver(rideAssignDriverDto));
    }

    @GetMapping("request-by-driver")
    public ResponseEntity<List<RideHistoryResponseDto>> getRequestByDriver(@RequestHeader("X-User-Id") Long driverId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(requestRideService.findAllByDriverId(driverId));
    }

    @GetMapping("unchosen-request")
    public ResponseEntity<List<RideHistoryResponseDto>> getUnChosenRide() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(requestRideService.findAllUnChosenRides());
    }
}
