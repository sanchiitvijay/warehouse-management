package com.warehouse.warehouse_management.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InventoryDto {

    private UUID id;
    public UUID productId;
    public UUID warehouseId;
    public ProductDto product;
    public WarehouseDto warehouse;
    public Integer quantity;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
