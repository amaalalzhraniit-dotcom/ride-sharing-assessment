package com.example.ridesharingdriversystem.business.service;

import com.example.ridesharingdriversystem.business.dto.PopulateDriverRequestDto;
import com.example.ridesharingdriversystem.business.dto.RideAssignDriverDto;
import com.example.ridesharingdriversystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingdriversystem.business.enums.DriverStatus;
import com.example.ridesharingdriversystem.client.CustomerClient;
import com.example.ridesharingdriversystem.presistence.domain.DriverEntity;
import com.example.ridesharingdriversystem.presistence.repo.DriverRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class DriverService {

    private final DriverRepo driverRepo;

    private final CustomerClient customerClient;

    @Transactional
    public Long updateDriverAvailability(Long userId) {
        DriverEntity driver = getValidateDriver(userId);
        Integer currentStatusId = driver.getStatus();
        int newStatusId = (currentStatusId != null && currentStatusId.equals(DriverStatus.OFFLINE.getId()))
                        ? DriverStatus.ONLINE.getId()
                        : DriverStatus.OFFLINE.getId();

        int updatedRows = driverRepo.updateDriverAvailability(driver.getId(), newStatusId);
        if (updatedRows == 0) {
            // Defensive check in case of concurrent delete
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found");
        }
        return driver.getId();
    }

    public List<RideHistoryResponseDto> getUnChosenRides() {
        return customerClient.getAvailableRides();
    }

    public Long assignDriver(Long userId, Long requestId) {
        DriverEntity driver = getValidateDriver(userId);

        if (driver.getStatus() != null &&
                driver.getStatus().equals(DriverStatus.OFFLINE.getId())) {
            log.error("Driver is offline");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Driver is offline");
        }

        RideAssignDriverDto request = new RideAssignDriverDto();
        request.setId(requestId);
        request.setDriverId(driver.getId());
        request.setDriverName(driver.getFullName());

        return customerClient.assignDriver(request);
    }

    public List<RideHistoryResponseDto> getRidesHistory(Long userId){
        DriverEntity driver=getValidateDriver(userId);
        return customerClient.getRidesHistory(driver.getId());
    }

    private DriverEntity getValidateDriver(Long driverId){
        return driverRepo.findByAuthUserId(driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));
    }


    @Transactional
    public Long populateDriver(PopulateDriverRequestDto requestDto) {
        DriverEntity newDriver = new DriverEntity();
        newDriver.setStatus(DriverStatus.OFFLINE.getId());
        newDriver.setFullName(requestDto.getFullName());
        newDriver.setAuthUserId(requestDto.getAuthId());
        return driverRepo.save(newDriver).getAuthUserId();
    }
}
