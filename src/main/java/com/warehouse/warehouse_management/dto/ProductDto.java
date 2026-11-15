package com.warehouse.warehouse_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    String name;
    String sku;
    String description;
    Float unitPrice;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
