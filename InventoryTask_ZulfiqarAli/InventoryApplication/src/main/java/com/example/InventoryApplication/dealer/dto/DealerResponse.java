package com.example.InventoryApplication.dealer.dto;

import com.example.InventoryApplication.dealer.entity.SubscriptionType;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class DealerResponse {
    private UUID id;
    private String name;
    private String email;
    private SubscriptionType subscriptionType;
}