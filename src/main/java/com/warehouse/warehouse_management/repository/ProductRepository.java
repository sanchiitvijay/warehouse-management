package com.warehouse.warehouse_management.repository;

import com.warehouse.warehouse_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
