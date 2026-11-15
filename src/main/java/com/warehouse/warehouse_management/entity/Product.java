package com.warehouse.warehouse_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table (
        name="product_table",
        uniqueConstraints = {@UniqueConstraint(name="uniqueSKU", columnNames = {"sku"})}
)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (length = 30, nullable = false)
    String name;

    @Column(length = 10, nullable = false)
    String sku;

    String description;

    @Column(precision = 10, scale = 2, nullable = false)
    BigDecimal unitPrice;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

}
