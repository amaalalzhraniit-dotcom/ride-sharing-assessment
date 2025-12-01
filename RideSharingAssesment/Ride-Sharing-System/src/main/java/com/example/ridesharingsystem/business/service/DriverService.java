package com.example.ridesharingsystem.business.service;

import com.example.ridesharingsystem.business.dto.PopulateDriverRequestDto;
import com.example.ridesharingsystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingsystem.clients.DriverClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverClient driverClient;

    public Long populateDriver(Long userId , String fullName){
        PopulateDriverRequestDto driverReq = new PopulateDriverRequestDto();
        driverReq.setAuthId(userId);
        driverReq.setFullName(fullName);
        try {
            return driverClient.populateDriver(driverReq);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Failed to create driver profile",
                    e
            );
        }
    }

    public Long updateAvailability(Long userId){
        return driverClient.updateAvailability(userId);
    }

    public List<RideHistoryResponseDto> getUnChosenRides(){
        return driverClient.getUnChosenRide();
    }

    public List<RideHistoryResponseDto> getRidesHistory(Long userId){
        return driverClient.getRidesHistory(userId);
    }

    public Long assignRide(Long userId, Long requestId) {
        return driverClient.assignDriver(userId,requestId);
    }
}
