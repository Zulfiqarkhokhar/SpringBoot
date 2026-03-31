package com.example.InventoryApplication.admin.controller;

import com.example.InventoryApplication.admin.dto.SubscriptionCountResponse;
import com.example.InventoryApplication.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/dealers/countBySubscription")
    public ResponseEntity<SubscriptionCountResponse> countDealersBySubscription() {
        SubscriptionCountResponse response = adminService.countDealersBySubscription();
        return ResponseEntity.ok(response);
    }
}