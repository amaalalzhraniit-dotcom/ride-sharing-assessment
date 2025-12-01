package com.example.ridesharingdriversystem.client;

import com.example.ridesharingdriversystem.business.dto.RideAssignDriverDto;
import com.example.ridesharingdriversystem.business.dto.RideHistoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(
        name = "customer-service",
        url = "${customer-service.base-url}"
)
public interface CustomerClient {

    @GetMapping("request-by-driver")
    List<RideHistoryResponseDto> getRidesHistory(@RequestHeader("X-User-Id") Long driverId);

    @GetMapping("unchosen-request")
    List<RideHistoryResponseDto> getAvailableRides();

    @PutMapping("update")
    Long assignDriver(@RequestBody RideAssignDriverDto request);
}