package com.example.ridesharingsystem.business.service;

import com.example.ridesharingsystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingsystem.business.dto.RideRequestDto;
import com.example.ridesharingsystem.clients.CustomerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerClient customerClient;

    public Long createRequest(Long userId , RideRequestDto dto){
        return customerClient.createRequest(userId,dto);
    }

    public List<RideHistoryResponseDto> getHistory(Long userId){
        return customerClient.getCustomerHistory(userId);
    }
}
