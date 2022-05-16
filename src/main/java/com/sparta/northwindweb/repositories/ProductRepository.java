package com.sparta.northwindweb.repositories;

import com.sparta.northwindweb.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}