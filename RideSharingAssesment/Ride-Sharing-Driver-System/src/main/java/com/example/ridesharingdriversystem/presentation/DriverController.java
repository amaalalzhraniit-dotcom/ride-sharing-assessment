package com.example.ridesharingdriversystem.presentation;

import com.example.ridesharingdriversystem.business.dto.PopulateDriverRequestDto;
import com.example.ridesharingdriversystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingdriversystem.business.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ride-request/")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping("update-availability")
    public ResponseEntity<Long> updateAvailability(@RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driverService.updateDriverAvailability(userId));
    }

    @GetMapping("get-unchosen-rides")
    public ResponseEntity<List<RideHistoryResponseDto>> getUnChosenRide() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driverService.getUnChosenRides());
    }

    @GetMapping("rides-history")
    public ResponseEntity<List<RideHistoryResponseDto>> getRidesHistory(@RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driverService.getRidesHistory(userId));
    }

    @GetMapping("assign-driver")
    public ResponseEntity<Long> assignDriver(@RequestHeader("X-User-Id") Long userId , @RequestParam Long requestId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driverService.assignDriver(userId,requestId));
    }

    @PostMapping("populate-driver")
    public ResponseEntity<Long> populateDriver(@RequestBody PopulateDriverRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driverService.populateDriver(requestDto));
    }
}
