package com.sparta.northwindweb.repositories;

import com.sparta.northwindweb.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}