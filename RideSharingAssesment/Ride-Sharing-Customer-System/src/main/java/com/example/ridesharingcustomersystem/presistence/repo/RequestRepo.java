package com.example.ridesharingcustomersystem.presistence.repo;

import com.example.ridesharingcustomersystem.presistence.domain.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<RequestEntity,Long> {

    List<RequestEntity> findAllByCustomerId(Long customerId);

    List<RequestEntity> findAllByDriverId(Long driverId);

    @Query(value = "select entity from RequestEntity entity where entity.requestState=:state")
    List<RequestEntity> findAllByState(Integer state);

}
