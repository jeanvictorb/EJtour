package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Testimony;

@Repository
public interface TestimonyRepository extends JpaRepository<Testimony, Long> {
    
}
