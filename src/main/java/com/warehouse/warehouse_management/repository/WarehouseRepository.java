package com.warehouse.warehouse_management.repository;

import com.warehouse.warehouse_management.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
    Warehouse findByCode(String code);

    @Query("SELECT w FROM Warehouse w where w.location LIKE %:location%")
    List<Warehouse> searchWarehouse(@Param("location") String location);
}
