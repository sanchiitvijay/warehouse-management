package com.warehouse.warehouse_management.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class WarehouseDto {
    private UUID id;
    String name;
    String code;
    String location;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
