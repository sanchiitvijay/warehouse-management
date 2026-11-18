package com.warehouse.warehouse_management.entity;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table (
        name="inventory_table",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_inventory_product_warehouse",
                columnNames = {"product_id", "warehouse_id"})
)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    public Product product;

    @ManyToOne(optional = false)
    public Warehouse warehouse;

    @Min(value = 0, message = "Value must be greater than or equal to 0")
    public Integer quantity;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;
}
