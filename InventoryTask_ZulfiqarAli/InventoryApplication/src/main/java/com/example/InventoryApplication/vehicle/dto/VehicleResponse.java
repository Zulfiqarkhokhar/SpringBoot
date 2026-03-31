package com.example.InventoryApplication.vehicle.dto;

import com.example.InventoryApplication.vehicle.entity.VehicleStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class VehicleResponse {
    private UUID id;
    private UUID dealerId;
    private String model;
    private BigDecimal price;
    private VehicleStatus status;
}