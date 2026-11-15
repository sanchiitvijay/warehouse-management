package com.warehouse.warehouse_management.controller;

import com.warehouse.warehouse_management.dto.ProductDto;
import com.warehouse.warehouse_management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/")
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto product
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getByID(
            @PathVariable UUID id
            ){
        return ResponseEntity.ok(productService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateByID(
            @PathVariable UUID id,
            @RequestBody ProductDto product
    ){
        return ResponseEntity.ok(productService.updateById(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(
            @RequestParam UUID id
    ){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
