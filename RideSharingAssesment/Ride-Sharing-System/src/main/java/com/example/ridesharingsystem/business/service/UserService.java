package com.example.ridesharingsystem.business.service;

import com.example.ridesharingsystem.business.dto.auth.UserRegistrationRequestDto;
import com.example.ridesharingsystem.business.enums.UserRole;
import com.example.ridesharingsystem.presistence.domain.UserEntity;
import com.example.ridesharingsystem.presistence.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final DriverService driverService;


    public Long registerUser(UserRegistrationRequestDto request) {

        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        UserRole role = UserRole.fromId(request.getRoleId());

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getPhone())
                .roleId(role.getId())
                .fullName(request.getFullName())
                .build();

        user = userRepo.save(user);

        if (role == UserRole.DRIVER) {
            driverService.populateDriver(user.getId() , user.getFullName());
        }
        return user.getId();
    }
}
