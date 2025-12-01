package com.example.ridesharingcustomersystem.business.utilitis;

import com.example.ridesharingcustomersystem.business.dto.RideHistoryResponseDto;
import com.example.ridesharingcustomersystem.business.dto.RideRequestDto;
import com.example.ridesharingcustomersystem.presistence.domain.RequestEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestEntityMapper {

    RequestEntity toEntity(RideRequestDto dto);

    RideHistoryResponseDto toDto(RequestEntity entity);

    List<RideHistoryResponseDto> toDto(List<RequestEntity> entities);

}
