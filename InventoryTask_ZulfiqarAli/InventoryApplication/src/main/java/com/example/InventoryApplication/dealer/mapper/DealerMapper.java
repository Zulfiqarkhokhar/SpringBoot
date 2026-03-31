package com.example.InventoryApplication.dealer.mapper;

import com.example.InventoryApplication.dealer.dto.DealerRequest;
import com.example.InventoryApplication.dealer.dto.DealerResponse;
import com.example.InventoryApplication.dealer.entity.Dealer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DealerMapper {

    DealerResponse toResponse(Dealer dealer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantId", ignore = true)
    Dealer toEntity(DealerRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantId", ignore = true)
    void updateEntity(DealerRequest request, @MappingTarget Dealer dealer);
}