package com.warehouse.warehouse_management.controller;

import com.warehouse.warehouse_management.dto.WarehouseDto;
import com.warehouse.warehouse_management.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping("/")
    public ResponseEntity<List<WarehouseDto>> getAllWarehouse(){
        return ResponseEntity.ok(warehouseService.getAllWarehouse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDto> getWarehouse(@PathVariable UUID id) {
        return ResponseEntity.ok(warehouseService.getWarehouseById(id));
    }

    @PostMapping("/")
    public ResponseEntity<WarehouseDto> createWarehouse(
            @RequestBody WarehouseDto warehouse
    ){
        System.out.println("warehouse------->"+warehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseService.createWarehouse(warehouse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDto> updateWarehouse (
            @RequestBody WarehouseDto warehouse,
            @PathVariable UUID id
    ){
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, warehouse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse (
            @PathVariable UUID id
    ){
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<WarehouseDto> getWarehouseByCode(
            @PathVariable String code
    ) {
        return ResponseEntity.ok(warehouseService.getByCode(code));
    }

    @GetMapping("/search")
    public ResponseEntity<List<WarehouseDto>> getWarehouseByLocation(
            @RequestParam("location") String location
    ){
        return ResponseEntity.ok(warehouseService.searchWarehouses(location));
    }
}
