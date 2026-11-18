package com.warehouse.warehouse_management.repository;

import com.warehouse.warehouse_management.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    List<Inventory> findByProductId(UUID productId);
    List<Inventory> findByWarehouseId(UUID warehouseId);
}
