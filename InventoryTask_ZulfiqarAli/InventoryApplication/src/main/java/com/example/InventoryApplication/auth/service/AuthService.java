package com.example.InventoryApplication.auth.service;


import com.example.InventoryApplication.auth.dto.LoginRequest;
import com.example.InventoryApplication.auth.dto.LoginResponse;

public interface AuthService {
    LoginResponse authenticate(LoginRequest request);
}