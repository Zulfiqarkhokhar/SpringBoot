package com.example.InventoryApplication.vehicle.repository;

import com.example.InventoryApplication.vehicle.entity.Vehicle;
import com.example.InventoryApplication.vehicle.entity.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    Optional<Vehicle> findByIdAndTenantId(UUID id, String tenantId);

    @Query("SELECT v FROM Vehicle v WHERE v.tenantId = :tenantId " +
            "AND (:model IS NULL OR LOWER(v.model) LIKE LOWER(CONCAT('%', :model, '%'))) " +
            "AND (:status IS NULL OR v.status = :status) " +
            "AND (:priceMin IS NULL OR v.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR v.price <= :priceMax)")
    Page<Vehicle> findAllWithFilters(@Param("tenantId") String tenantId,
                                     @Param("model") String model,
                                     @Param("status") VehicleStatus status,
                                     @Param("priceMin") BigDecimal priceMin,
                                     @Param("priceMax") BigDecimal priceMax,
                                     Pageable pageable);

    @Query("SELECT v FROM Vehicle v WHERE v.tenantId = :tenantId " +
            "AND v.dealerId IN (SELECT d.id FROM Dealer d WHERE d.tenantId = :tenantId AND d.subscriptionType = 'PREMIUM') " +
            "AND (:model IS NULL OR LOWER(v.model) LIKE LOWER(CONCAT('%', :model, '%'))) " +
            "AND (:status IS NULL OR v.status = :status) " +
            "AND (:priceMin IS NULL OR v.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR v.price <= :priceMax)")
    Page<Vehicle> findAllByPremiumDealers(@Param("tenantId") String tenantId,
                                          @Param("model") String model,
                                          @Param("status") VehicleStatus status,
                                          @Param("priceMin") BigDecimal priceMin,
                                          @Param("priceMax") BigDecimal priceMax,
                                          Pageable pageable);
}