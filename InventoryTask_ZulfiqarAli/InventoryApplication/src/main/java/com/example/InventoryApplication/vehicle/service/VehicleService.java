package com.example.InventoryApplication.vehicle.service;

import com.example.InventoryApplication.comon.dto.PageResponse;
import com.example.InventoryApplication.vehicle.dto.VehicleRequest;
import com.example.InventoryApplication.vehicle.dto.VehicleResponse;
import com.example.InventoryApplication.vehicle.entity.VehicleStatus;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface VehicleService {
    VehicleResponse createVehicle(VehicleRequest request);
    VehicleResponse getVehicleById(UUID id);
    PageResponse<VehicleResponse> getAllVehicles(String model, VehicleStatus status,
                                                 BigDecimal priceMin, BigDecimal priceMax,
                                                 Boolean premiumOnly, Pageable pageable);
    VehicleResponse updateVehicle(UUID id, VehicleRequest request);
    void deleteVehicle(UUID id);
}