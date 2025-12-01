package com.example.ridesharingsystem.presentation;

import com.example.ridesharingsystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingsystem.business.dto.RideRequestDto;
import com.example.ridesharingsystem.business.dto.auth.CustomUserDetails;
import com.example.ridesharingsystem.business.service.CustomerService;
import com.example.ridesharingsystem.business.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/driver/")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("update-availability")
    public ResponseEntity<Long> updateAvailability(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driverService.updateAvailability(customUserDetails.getId()));
    }

    @GetMapping("get-history")
    public ResponseEntity<List<RideHistoryResponseDto>> getHistory(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driverService.getRidesHistory(customUserDetails.getId()));
    }

    @GetMapping("get-unchosen-rides")
    public ResponseEntity<List<RideHistoryResponseDto>> getUnchsenRide(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driverService.getUnChosenRides());
    }

    @PostMapping("assign-ride")
    public ResponseEntity<Long> assignRides(@AuthenticationPrincipal CustomUserDetails customUserDetails , @RequestParam Long requestId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driverService.assignRide(customUserDetails.getId(),requestId));
    }

}
