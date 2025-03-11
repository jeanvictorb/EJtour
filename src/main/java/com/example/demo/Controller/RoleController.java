package com.example.demo.Controller;

import com.example.demo.Entity.Role;
import com.example.demo.Repository.RoleRepository;
import org.springframework.http.HttpStatus;
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
        try {
            if (request.getName() == null || request.getName().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome do papel é obrigatório.");
            }

            Role role = new Role();
            role.setName(request.getName());

            roleRepository.save(role);
            return ResponseEntity.status(HttpStatus.CREATED).body("Papel criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar o papel: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        try {
            List<Role> roles = roleRepository.findAll();
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar papéis: " + e.getMessage());
        }
    }

    public static class RoleRequest {
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
