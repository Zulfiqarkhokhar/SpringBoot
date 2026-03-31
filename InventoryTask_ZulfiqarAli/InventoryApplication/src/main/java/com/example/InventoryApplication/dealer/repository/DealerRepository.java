package com.example.InventoryApplication.dealer.repository;

import com.example.InventoryApplication.dealer.entity.Dealer;
import com.example.InventoryApplication.dealer.entity.SubscriptionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, UUID> {

    Optional<Dealer> findByIdAndTenantId(UUID id, String tenantId);

    Page<Dealer> findAllByTenantId(String tenantId, Pageable pageable);

    boolean existsByEmailAndTenantId(String email, String tenantId);

    @Query("SELECT COUNT(d) FROM Dealer d WHERE d.subscriptionType = :subscriptionType")
    long countByTenantIdAndSubscriptionType(@Param("subscriptionType") SubscriptionType subscriptionType);

    @Query("SELECT d.subscriptionType, COUNT(d) FROM Dealer d WHERE d.tenantId = :tenantId GROUP BY d.subscriptionType")
    Object[] countBySubscriptionTypeGrouped(@Param("tenantId") String tenantId);
}