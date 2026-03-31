package com.example.InventoryApplication.vehicle.dto;

import com.example.InventoryApplication.vehicle.entity.VehicleStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class VehicleRequest {

    @NotNull(message = "Dealer ID is required")
    private UUID dealerId;

    @NotBlank(message = "Model is required")
    @Size(min = 2, max = 100, message = "Model must be between 2 and 100 characters")
    private String model;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Price must be less than 1 billion")
    private BigDecimal price;

    @NotNull(message = "Status is required")
    private VehicleStatus status;
}