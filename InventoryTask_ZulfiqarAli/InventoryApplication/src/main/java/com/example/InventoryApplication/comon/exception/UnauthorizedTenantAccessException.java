package com.example.InventoryApplication.comon.exception;

public class UnauthorizedTenantAccessException extends RuntimeException {
    public UnauthorizedTenantAccessException(String message) {
        super(message);
    }
}