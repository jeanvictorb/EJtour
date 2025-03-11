package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Testimony;

@Repository
public interface TestimonyRepository extends JpaRepository<Testimony, Long> {
    Optional<Testimony> findById(long id);
    List<Testimony> findAll(long id);
}
