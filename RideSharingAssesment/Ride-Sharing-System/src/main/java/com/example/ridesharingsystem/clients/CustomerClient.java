package com.example.ridesharingsystem.clients;

import com.example.ridesharingsystem.business.dto.PopulateDriverRequestDto;
import com.example.ridesharingsystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingsystem.business.dto.RideRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(
        name = "customer-service",
        url = "${customer-service.base-url}"
)
public interface CustomerClient {

    @PostMapping("create")
    Long createRequest(@RequestHeader("X-User-Id") Long customerId , @RequestBody RideRequestDto rideRequestDto);

    @GetMapping("request-history")
     List<RideHistoryResponseDto> getCustomerHistory(@RequestHeader("X-User-Id") Long customerId);

    }