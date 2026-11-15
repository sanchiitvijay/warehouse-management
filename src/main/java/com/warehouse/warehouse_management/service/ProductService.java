package com.warehouse.warehouse_management.service;

import com.warehouse.warehouse_management.dto.ProductDto;
import com.warehouse.warehouse_management.entity.Product;
import com.warehouse.warehouse_management.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductDto getById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return modelMapper.map(product, ProductDto.class);
    }

    public ProductDto createProduct(ProductDto product) {
        Product p = modelMapper.map(product, Product.class);
        Product newProduct = productRepository.save(p);

        return modelMapper.map(newProduct, ProductDto.class);
    }

    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return products;
    }

    public ProductDto updateById(UUID id, ProductDto product) {
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        modelMapper.map(product, oldProduct);
        Product updatedProduct = productRepository.save(oldProduct);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }


    public Void deleteById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
        return null;
    }
}
