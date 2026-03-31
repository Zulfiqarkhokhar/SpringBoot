package com.example.InventoryApplication.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String username;
    private String role;
    private boolean authenticated;
    private String message;
}