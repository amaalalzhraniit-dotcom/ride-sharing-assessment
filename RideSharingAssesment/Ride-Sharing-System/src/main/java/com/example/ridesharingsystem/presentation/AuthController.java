package com.example.ridesharingsystem.presentation;

import com.example.ridesharingsystem.business.dto.auth.UserRegistrationRequestDto;
import com.example.ridesharingsystem.business.dto.auth.CustomUserDetails;
import com.example.ridesharingsystem.business.dto.auth.LoginRequestDto;
import com.example.ridesharingsystem.business.dto.auth.LoginResponseDto;
import com.example.ridesharingsystem.business.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody UserRegistrationRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request,
                                                  HttpServletRequest httpRequest) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        HttpSession session = httpRequest.getSession(true);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                securityContext
        );

        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

        // 6) Extract role from authorities
        String role = authentication.getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority) // e.g. "ROLE_CUSTOMER"
                .orElse(null);

        LoginResponseDto response = new LoginResponseDto(
                principal.getId(),
                principal.getUsername(),
                role
        );
        return ResponseEntity.ok(response);
    }
}
