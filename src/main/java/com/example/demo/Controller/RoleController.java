package com.example.demo.Controller;

import com.example.demo.Entity.Role;

import com.example.demo.Repository.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());

        roleRepository.save(role);
        return ResponseEntity.ok("Papel criado com sucesso!");
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public static class RoleRequest {
        private Role name;

        public Role getName() { return name; }
        public void setName(Role name) { this.name = name; }
    }
}
