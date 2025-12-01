package com.example.ridesharingsystem.presentation;

import com.example.ridesharingsystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingsystem.business.dto.RideRequestDto;
import com.example.ridesharingsystem.business.dto.auth.CustomUserDetails;
import com.example.ridesharingsystem.business.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("create")
    public ResponseEntity<Long> create(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       @RequestBody RideRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.createRequest(customUserDetails.getId(),dto));
    }

    @GetMapping("get-history")
    public ResponseEntity<List<RideHistoryResponseDto>> getHistory(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.getHistory(customUserDetails.getId()));
    }

}
