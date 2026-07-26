package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.modal.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo extends JpaRepository<product,Integer> {
}
