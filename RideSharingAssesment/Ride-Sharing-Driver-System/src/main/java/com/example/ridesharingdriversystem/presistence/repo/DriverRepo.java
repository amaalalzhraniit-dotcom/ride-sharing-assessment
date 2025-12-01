package com.example.ridesharingdriversystem.presistence.repo;

import com.example.ridesharingdriversystem.presistence.domain.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepo extends JpaRepository<DriverEntity,Long> {
    @Modifying
    @Query("update DriverEntity d set d.status =:status where d.id =:driverId")
    Integer updateDriverAvailability(Long driverId , Integer status);

    Optional<DriverEntity> findByAuthUserId(Long userId);
}
