package com.example.ridesharingsystem.clients;

import com.example.ridesharingsystem.business.dto.PopulateDriverRequestDto;
import com.example.ridesharingsystem.business.dto.RideHistoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "driver-service",
        url = "${driver-service.base-url}"
)
public interface DriverClient {

    @PostMapping("populate-driver")
    Long populateDriver(@RequestBody PopulateDriverRequestDto request);

    @PostMapping("update-availability")
    Long updateAvailability(@RequestHeader("X-User-Id") Long userId);

    @GetMapping("get-unchosen-rides")
    List<RideHistoryResponseDto> getUnChosenRide();

    @GetMapping("rides-history")
    List<RideHistoryResponseDto> getRidesHistory(@RequestHeader("X-User-Id") Long userId);

    @GetMapping("assign-driver")
    Long assignDriver(@RequestHeader("X-User-Id") Long driverId , @RequestParam Long requestId);
}
