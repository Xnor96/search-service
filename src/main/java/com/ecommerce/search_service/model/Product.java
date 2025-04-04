package com.ecommerce.search_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    
    private String name;
    private String description;
    private String category;
    private String brand;
    private Double price;
    private Integer stock;
    private String imageUrl;
}