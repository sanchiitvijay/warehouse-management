package com.warehouse.warehouse_management.controller;

import com.warehouse.warehouse_management.dto.InventoryDto;
import com.warehouse.warehouse_management.entity.Inventory;
import com.warehouse.warehouse_management.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/")
    public ResponseEntity<List<InventoryDto>> getAllInventories(){
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getInventory (
            @PathVariable UUID id
            ) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @PostMapping("/")
    public ResponseEntity<InventoryDto> createInventory(
            @RequestBody InventoryDto inventory
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createInventory(inventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> updateInventory(
            @RequestBody InventoryDto inventory,
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(
            @PathVariable UUID id
    ) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<InventoryDto>> getInventoriesbyProductId (
            @PathVariable UUID productId
    ) {
        return ResponseEntity.ok(inventoryService.getInventoriesbyProductId(productId));
    }

    @GetMapping("warehouse/{warehouseId}")
    public ResponseEntity<List<InventoryDto>> getInventoriesByWarehouseId (
            @PathVariable UUID warehouseId
    ) {
        return ResponseEntity.ok(inventoryService.getInventoriesByWarehouseId(warehouseId));
    }
}
