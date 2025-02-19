package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Testimony;

public interface TestimonyRepository extends JpaRepository<Testimony, Long> {
    
}
