package com.example.InventoryApplication.vehicle.mapper;

import com.example.InventoryApplication.vehicle.dto.VehicleRequest;
import com.example.InventoryApplication.vehicle.dto.VehicleResponse;
import com.example.InventoryApplication.vehicle.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehicleMapper {

    VehicleResponse toResponse(Vehicle vehicle);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantId", ignore = true)
    Vehicle toEntity(VehicleRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantId", ignore = true)
    void updateEntity(VehicleRequest request, @MappingTarget Vehicle vehicle);
}