package com.example.InventoryApplication.vehicle.service;

import com.example.InventoryApplication.comon.dto.PageResponse;
import com.example.InventoryApplication.comon.exception.BusinessException;
import com.example.InventoryApplication.comon.security.TenantContext;
import com.example.InventoryApplication.dealer.entity.Dealer;
import com.example.InventoryApplication.dealer.repository.DealerRepository;
import com.example.InventoryApplication.vehicle.dto.VehicleRequest;
import com.example.InventoryApplication.vehicle.dto.VehicleResponse;
import com.example.InventoryApplication.vehicle.entity.Vehicle;
import com.example.InventoryApplication.vehicle.entity.VehicleStatus;
import com.example.InventoryApplication.vehicle.mapper.VehicleMapper;
import com.example.InventoryApplication.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DealerRepository dealerRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleResponse createVehicle(VehicleRequest request) {
        String tenantId = TenantContext.getCurrentTenant();

        Dealer dealer = dealerRepository.findByIdAndTenantId(request.getDealerId(), tenantId)
                .orElseThrow(() -> new BusinessException("Dealer not found"));

        Vehicle vehicle = vehicleMapper.toEntity(request);
        vehicle.setTenantId(tenantId);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        log.info("Created vehicle with id: {} for tenant: {}", savedVehicle.getId(), tenantId);

        return vehicleMapper.toResponse(savedVehicle);
    }

    @Override
    public VehicleResponse getVehicleById(UUID id) {
        String tenantId = TenantContext.getCurrentTenant();

        Vehicle vehicle = vehicleRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new BusinessException("Vehicle not found"));

        return vehicleMapper.toResponse(vehicle);
    }

    @Override
    public PageResponse<VehicleResponse> getAllVehicles(String model, VehicleStatus status,
                                                        BigDecimal priceMin, BigDecimal priceMax,
                                                        Boolean premiumOnly, Pageable pageable) {
        String tenantId = TenantContext.getCurrentTenant();

        Page<Vehicle> vehiclesPage;

        if (premiumOnly != null && premiumOnly) {
            vehiclesPage = vehicleRepository.findAllByPremiumDealers(
                    tenantId, model, status, priceMin, priceMax, pageable);
        } else {
            vehiclesPage = vehicleRepository.findAllWithFilters(
                    tenantId, model, status, priceMin, priceMax, pageable);
        }

        return PageResponse.<VehicleResponse>builder()
                .content(vehiclesPage.map(vehicleMapper::toResponse).getContent())
                .pageNumber(vehiclesPage.getNumber())
                .pageSize(vehiclesPage.getSize())
                .totalElements(vehiclesPage.getTotalElements())
                .totalPages(vehiclesPage.getTotalPages())
                .last(vehiclesPage.isLast())
                .build();
    }

    @Override
    public VehicleResponse updateVehicle(UUID id, VehicleRequest request) {
        String tenantId = TenantContext.getCurrentTenant();

        Vehicle vehicle = vehicleRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new BusinessException("Vehicle not found"));

        Dealer dealer = dealerRepository.findByIdAndTenantId(request.getDealerId(), tenantId)
                .orElseThrow(() -> new BusinessException("Dealer not found"));

        vehicleMapper.updateEntity(request, vehicle);

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        log.info("Updated vehicle with id: {} for tenant: {}", updatedVehicle.getId(), tenantId);

        return vehicleMapper.toResponse(updatedVehicle);
    }

    @Override
    public void deleteVehicle(UUID id) {
        String tenantId = TenantContext.getCurrentTenant();

        Vehicle vehicle = vehicleRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new BusinessException("Vehicle not found"));

        vehicleRepository.delete(vehicle);
        log.info("Deleted vehicle with id: {} for tenant: {}", id, tenantId);
    }
}