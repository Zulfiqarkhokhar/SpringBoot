package com.example.InventoryApplication.admin.service;

import com.example.InventoryApplication.admin.dto.SubscriptionCountResponse;
import com.example.InventoryApplication.comon.exception.BusinessException;
import com.example.InventoryApplication.dealer.entity.SubscriptionType;
import com.example.InventoryApplication.dealer.repository.DealerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final DealerRepository dealerRepository;

    @Override
    public SubscriptionCountResponse countDealersBySubscription() {
        try {
            long basicCount = dealerRepository.countByTenantIdAndSubscriptionType(SubscriptionType.BASIC);
            long premiumCount = dealerRepository.countByTenantIdAndSubscriptionType(SubscriptionType.PREMIUM);

            log.info("Admin counted dealers by subscription - BASIC: {}, PREMIUM: {} (GLOBAL COUNT)", basicCount, premiumCount);

            return SubscriptionCountResponse.builder()
                    .basic(basicCount)
                    .premium(premiumCount)
                    .globalCount(true)
                    .build();
        } catch (Exception e) {
            log.error("Error counting dealers by subscription", e);
            throw new BusinessException("Failed to count dealers by subscription");
        }
    }
}