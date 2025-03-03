package com.ecommerce.search_service.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecommerce.search_service.model.Product;
import com.ecommerce.search_service.repository.ProductRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Borra todos los productos existentes
        productRepository.deleteAll();

        // Crea productos de ejemplo
        List<Product> products = Arrays.asList(
            new Product("1", "Smartphone XY Pro", "Último modelo con cámara de alta resolución", "Electrónica", "TechX", 599.99, 15, "/api/placeholder/300/300"),
            new Product("2", "Laptop UltraBook", "Perfecta para trabajo y entretenimiento", "Electrónica", "TechX", 999.99, 8, "/api/placeholder/300/300"),
            new Product("3", "Auriculares Wireless", "Sonido de alta calidad con cancelación de ruido", "Electrónica", "AudioTech", 199.99, 20, "/api/placeholder/300/300"),
            new Product("4", "Zapatillas Running", "Ideales para correr largas distancias", "Deportes", "SportBrand", 89.99, 30, "/api/placeholder/300/300"),
            new Product("5", "Camiseta Deportiva", "Material transpirable de alta calidad", "Ropa", "FashionX", 29.99, 50, "/api/placeholder/300/300")        );

        // Guarda los productos
        productRepository.saveAll(products);
    }
}