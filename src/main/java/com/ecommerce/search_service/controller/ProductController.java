package com.ecommerce.search_service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.search_service.model.Product;
import com.ecommerce.search_service.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
        List<Product> products = productRepository.findByNameContainingOrDescriptionContaining(query, query);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        List<Product> products = productRepository.findByCategory(category);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/facets")
    public ResponseEntity<Map<String, Object>> getFacets() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        
        // Crear facets
        Map<String, Long> categoryCounts = new HashMap<>();
        Map<String, Long> brandCounts = new HashMap<>();
        
        for (Product product : products) {
            // Contar categorías
            categoryCounts.put(
                product.getCategory(), 
                categoryCounts.getOrDefault(product.getCategory(), 0L) + 1
            );
            
            // Contar marcas
            brandCounts.put(
                product.getBrand(), 
                brandCounts.getOrDefault(product.getBrand(), 0L) + 1
            );
        }
        
        Map<String, Object> facets = new HashMap<>();
        facets.put("categories", categoryCounts);
        facets.put("brands", brandCounts);
        
        return ResponseEntity.ok(facets);
    }
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }
}