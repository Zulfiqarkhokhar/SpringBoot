package com.example.InventoryApplication.admin.service;


import com.example.InventoryApplication.admin.dto.SubscriptionCountResponse;

public interface AdminService {
    SubscriptionCountResponse countDealersBySubscription();
}