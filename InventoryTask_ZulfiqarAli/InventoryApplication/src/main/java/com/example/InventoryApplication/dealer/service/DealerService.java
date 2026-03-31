package com.example.InventoryApplication.dealer.service;

import com.example.InventoryApplication.comon.dto.PageResponse;
import com.example.InventoryApplication.dealer.dto.DealerRequest;
import com.example.InventoryApplication.dealer.dto.DealerResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DealerService {
    DealerResponse createDealer(DealerRequest request);
    DealerResponse getDealerById(UUID id);
    PageResponse<DealerResponse> getAllDealers(Pageable pageable);
    DealerResponse updateDealer(UUID id, DealerRequest request);
    void deleteDealer(UUID id);
}