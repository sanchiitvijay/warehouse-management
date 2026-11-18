package com.warehouse.warehouse_management.service;

import com.warehouse.warehouse_management.dto.InventoryDto;
import com.warehouse.warehouse_management.entity.Inventory;
import com.warehouse.warehouse_management.entity.Product;
import com.warehouse.warehouse_management.entity.Warehouse;
import com.warehouse.warehouse_management.repository.InventoryRepository;
import com.warehouse.warehouse_management.repository.ProductRepository;
import com.warehouse.warehouse_management.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final ModelMapper modelMapper;

    public List<InventoryDto> getAllInventories() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDto.class))
                .toList();
    }

    public InventoryDto getInventoryById(UUID id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory Not Found"));
        return modelMapper.map(inventory, InventoryDto.class);
    }

    public InventoryDto createInventory(InventoryDto inventory) {
        Product product = productRepository.findById(inventory.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        Warehouse warehouse = warehouseRepository.findById(inventory.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        Inventory newInventory = new Inventory();
        newInventory.setProduct(product);
        newInventory.setWarehouse(warehouse);
        newInventory.setQuantity(inventory.getQuantity());

        Inventory savedInventory = inventoryRepository.save(newInventory);
        return modelMapper.map(savedInventory, InventoryDto.class);
    }

    public InventoryDto updateInventory(UUID id, InventoryDto inventory) {
        Inventory oldInventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory Not Found"));
        modelMapper.map(inventory, oldInventory);
        Inventory newInventory = inventoryRepository.save(oldInventory);
        return modelMapper.map(oldInventory, InventoryDto.class);
    }

    public Void deleteInventory(UUID id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory Not Found"));
        inventoryRepository.delete(inventory);
        return null;
    }

    // Get stock across all warehouses for a product
    public List<InventoryDto> getInventoriesbyProductId(UUID productId) {
        return inventoryRepository.findByProductId(productId)
                .stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDto.class))
                .toList();
    }

    // Get all stock in a warehouse
    public List<InventoryDto> getInventoriesByWarehouseId(UUID warehouseId) {
        return inventoryRepository.findByWarehouseId(warehouseId)
                .stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDto.class))
                .toList();
    }
}