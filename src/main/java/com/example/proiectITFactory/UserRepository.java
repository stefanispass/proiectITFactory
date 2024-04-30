package com.example.proiectITFactory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> { //long = tipul ID-ului, folosim Jpa pentru ca are deja metode implementate (existsById, findAll, etc.)
    @Query("SELECT u from User u WHERE u.email = ?1")
    User findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User getUserById(Long id);
}
