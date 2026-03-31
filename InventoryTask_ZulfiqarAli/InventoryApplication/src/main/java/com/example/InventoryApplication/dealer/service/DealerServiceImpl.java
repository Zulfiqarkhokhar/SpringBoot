package com.example.InventoryApplication.dealer.service;

import com.example.InventoryApplication.comon.dto.PageResponse;
import com.example.InventoryApplication.comon.exception.BusinessException;
import com.example.InventoryApplication.comon.security.TenantContext;
import com.example.InventoryApplication.dealer.dto.DealerRequest;
import com.example.InventoryApplication.dealer.dto.DealerResponse;
import com.example.InventoryApplication.dealer.entity.Dealer;
import com.example.InventoryApplication.dealer.mapper.DealerMapper;
import com.example.InventoryApplication.dealer.repository.DealerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DealerServiceImpl implements DealerService {

    private final DealerRepository dealerRepository;
    private final DealerMapper dealerMapper;

    @Override
    public DealerResponse createDealer(DealerRequest request) {
        String tenantId = TenantContext.getCurrentTenant();

        if (dealerRepository.existsByEmailAndTenantId(request.getEmail(), tenantId)) {
            throw new BusinessException("Dealer with email " + request.getEmail() + " already exists");
        }

        Dealer dealer = dealerMapper.toEntity(request);
        dealer.setTenantId(tenantId);

        Dealer savedDealer = dealerRepository.save(dealer);
        log.info("Created dealer with id: {} for tenant: {}", savedDealer.getId(), tenantId);

        return dealerMapper.toResponse(savedDealer);
    }

    @Override
    public DealerResponse getDealerById(UUID id) {
        String tenantId = TenantContext.getCurrentTenant();

        Dealer dealer = dealerRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new BusinessException("Dealer not found"));

        return dealerMapper.toResponse(dealer);
    }

    @Override
    public PageResponse<DealerResponse> getAllDealers(Pageable pageable) {
        String tenantId = TenantContext.getCurrentTenant();

        Page<Dealer> dealersPage = dealerRepository.findAllByTenantId(tenantId, pageable);

        return PageResponse.<DealerResponse>builder()
                .content(dealersPage.map(dealerMapper::toResponse).getContent())
                .pageNumber(dealersPage.getNumber())
                .pageSize(dealersPage.getSize())
                .totalElements(dealersPage.getTotalElements())
                .totalPages(dealersPage.getTotalPages())
                .last(dealersPage.isLast())
                .build();
    }

    @Override
    public DealerResponse updateDealer(UUID id, DealerRequest request) {
        String tenantId = TenantContext.getCurrentTenant();

        Dealer dealer = dealerRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new BusinessException("Dealer not found"));

        if (!dealer.getEmail().equals(request.getEmail()) &&
                dealerRepository.existsByEmailAndTenantId(request.getEmail(), tenantId)) {
            throw new BusinessException("Dealer with email " + request.getEmail() + " already exists");
        }

        dealerMapper.updateEntity(request, dealer);

        Dealer updatedDealer = dealerRepository.save(dealer);
        log.info("Updated dealer with id: {} for tenant: {}", updatedDealer.getId(), tenantId);

        return dealerMapper.toResponse(updatedDealer);
    }

    @Override
    public void deleteDealer(UUID id) {
        String tenantId = TenantContext.getCurrentTenant();

        Dealer dealer = dealerRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new BusinessException("Dealer not found"));

        dealerRepository.delete(dealer);
        log.info("Deleted dealer with id: {} for tenant: {}", id, tenantId);
    }
}