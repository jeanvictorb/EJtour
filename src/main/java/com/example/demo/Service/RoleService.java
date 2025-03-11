package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.entity.Role;
import app.repository.RoleRepository;
import jakarta.persistence.EntityExistsException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<Role> findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()){
            throw new EntityExistsException("Permissão não encontrada para o ID: " + id);
        }
        return ResponseEntity.ok(role.get());
    }

    public ResponseEntity<Role> postMapping(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role não pode ser nulo");
        }
        Role savedRole = roleRepository.save(role);
        return ResponseEntity.status(201).body(savedRole);
    }

    public ResponseEntity<List<Role>> findAll() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(roles);
    }
}
