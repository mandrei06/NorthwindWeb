package com.sparta.northwindweb.repositories;

import com.sparta.northwindweb.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}