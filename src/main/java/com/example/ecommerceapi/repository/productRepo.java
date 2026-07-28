package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.modal.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepo extends JpaRepository<product,Integer> {

    @Query(value = "SELECT * FROM product p " +
            "WHERE p.name ILIKE CONCAT('%', :keyword, '%') " +
            "OR p.description ILIKE CONCAT('%', :keyword, '%') " +
            "OR p.brand ILIKE CONCAT('%', :keyword, '%') " +
            "OR p.category ILIKE CONCAT('%', :keyword, '%')",
            nativeQuery = true)
    List<product> searchById(String search);
}
