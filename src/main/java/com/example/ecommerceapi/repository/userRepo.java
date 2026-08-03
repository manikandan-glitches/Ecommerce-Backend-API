package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<User,String> {
    User findByUsername(String username);
}
