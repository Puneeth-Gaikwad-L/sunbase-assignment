package com.example.sunbaseassignment.repository;

import com.example.sunbaseassignment.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepository extends JpaRepository<Customer, Integer> {
}
