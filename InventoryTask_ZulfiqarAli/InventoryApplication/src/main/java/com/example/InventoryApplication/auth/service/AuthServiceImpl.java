package com.example.InventoryApplication.auth.service;

import com.example.InventoryApplication.auth.dto.LoginRequest;
import com.example.InventoryApplication.auth.dto.LoginResponse;
import com.example.InventoryApplication.comon.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("ROLE_USER");

            log.info("User {} authenticated successfully", request.getUsername());

            return LoginResponse.builder()
                    .username(request.getUsername())
                    .role(role.replace("ROLE_", ""))
                    .authenticated(true)
                    .message("Login successful")
                    .build();

        } catch (Exception e) {
            log.error("Authentication failed for user: {}", request.getUsername(), e);
            throw new BusinessException("Invalid username or password");
        }
    }
}