package com.ecommerce.search_service.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.search_service.model.Product;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
    List<Product> findByCategory(String category);
    List<Product> findByBrand(String brand);
    
    @Query("{\"bool\": {\"must\": [{\"match\": {\"description\": \"?0\"}}]}}")
    List<Product> findByDescriptionMatching(String description);
}