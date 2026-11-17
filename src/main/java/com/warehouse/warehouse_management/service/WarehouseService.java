package com.warehouse.warehouse_management.service;

import com.warehouse.warehouse_management.dto.WarehouseDto;
import com.warehouse.warehouse_management.entity.Warehouse;
import com.warehouse.warehouse_management.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ModelMapper modelMapper;

    public List<WarehouseDto> getAllWarehouse(){
        List<WarehouseDto> warehouses= warehouseRepository.findAll()
                .stream()
                .map(warehouse -> modelMapper.map(warehouse, WarehouseDto.class))
                .toList();

        return warehouses;
    }

    public WarehouseDto getWarehouseById(UUID id){
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not found"));

        return modelMapper.map(warehouse, WarehouseDto.class);
    }

    public WarehouseDto createWarehouse(WarehouseDto warehouse){
        Warehouse w = modelMapper.map(warehouse, Warehouse.class);
        Warehouse newWarehouse = warehouseRepository.save(w);

        return modelMapper.map(newWarehouse, WarehouseDto.class);
    }

    public WarehouseDto updateWarehouse(UUID id, WarehouseDto warehouse) {
        Warehouse oldWarehouse = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not found"));
        modelMapper.map(warehouse, oldWarehouse);
        Warehouse updatedWarehouse = warehouseRepository.save(oldWarehouse);
        return modelMapper.map(updatedWarehouse, WarehouseDto.class);
    }

    public Void deleteWarehouse(UUID id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not found"));

        warehouseRepository.delete(warehouse);
        return null;
    }

    public WarehouseDto getByCode(String code) {
        Warehouse warehouse = warehouseRepository.findByCode(code);
        return modelMapper.map(warehouse, WarehouseDto.class);
    }

    public List<WarehouseDto> searchWarehouses (String name) {
        List<WarehouseDto> warehouses= warehouseRepository.searchWarehouse(name)
                .stream()
                .map(warehouse -> modelMapper.map(warehouse, WarehouseDto.class))
                .toList();

        return warehouses;
    }



}
