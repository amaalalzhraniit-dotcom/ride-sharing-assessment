package com.example.ridesharingcustomersystem.business.service;

import com.example.ridesharingcustomersystem.business.dto.RideAssignDriverDto;
import com.example.ridesharingcustomersystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingcustomersystem.business.dto.RideRequestDto;
import com.example.ridesharingcustomersystem.business.enums.RideRequestState;
import com.example.ridesharingcustomersystem.business.utilitis.RequestEntityMapper;
import com.example.ridesharingcustomersystem.presistence.domain.RequestEntity;
import com.example.ridesharingcustomersystem.presistence.repo.RequestRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestRideService {

    private final RequestRepo requestRepo;

    private final RequestEntityMapper mapper;

    @Transactional
    public Long createRequest(Long customerId , RideRequestDto request){
        RequestEntity entity = mapper.toEntity(request);
        entity.setRequestState(RideRequestState.NEW.getCode());
        entity.setCustomerId(customerId);
        return requestRepo.save(entity).getId();
    }

    @Transactional
    public Long assignDriver(RideAssignDriverDto rideAssignDriverDto){
        RequestEntity entity = requestRepo.findById(rideAssignDriverDto.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found"));
        if (entity.getRequestState() != null &&
                !entity.getRequestState().equals(RideRequestState.NEW.getCode())
                &&!entity.getRequestState().equals(RideRequestState.DRIVER_ASSIGNED.getCode())) {
            log.error("cannot assign driver state is {}",entity.getRequestState());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "cannot assign driver");
        }
        entity.setRequestState(RideRequestState.DRIVER_ASSIGNED.getCode());
        entity.setDriverId(rideAssignDriverDto.getDriverId());
        entity.setDriverName(rideAssignDriverDto.getDriverName());
        return requestRepo.save(entity).getId();
    }

    public List<RideHistoryResponseDto> findAllByCustomerId(Long customerId){
        List<RequestEntity> entites = requestRepo.findAllByCustomerId(customerId);
        return mapper.toDto(entites);
    }

    public List<RideHistoryResponseDto> findAllByDriverId(Long customerId){
        List<RequestEntity> entites = requestRepo.findAllByDriverId(customerId);
        return mapper.toDto(entites);
    }

    public List<RideHistoryResponseDto> findAllUnChosenRides(){
        List<RequestEntity> entites = requestRepo.findAllByState(RideRequestState.NEW.getCode());
        return mapper.toDto(entites);
    }
}
