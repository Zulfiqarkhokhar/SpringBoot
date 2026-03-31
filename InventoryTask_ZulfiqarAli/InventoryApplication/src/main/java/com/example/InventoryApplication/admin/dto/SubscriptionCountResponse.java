package com.example.InventoryApplication.admin.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionCountResponse {
    private Long basic;
    private Long premium;
    private boolean globalCount;
}