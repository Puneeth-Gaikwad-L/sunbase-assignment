package com.example.sunbaseassignment.repository;

import com.example.sunbaseassignment.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface customerRepository extends JpaRepository<Customer, Integer> {

    public Customer findByEmail(String email);

    public long deleteByEmail(String email);

}
