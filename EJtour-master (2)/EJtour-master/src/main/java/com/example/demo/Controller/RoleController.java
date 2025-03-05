

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

    // ✅ Criar um novo papel
    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());

        roleRepository.save(role);
        return ResponseEntity.ok("Papel criado com sucesso!");
    }

    // ✅ Listar todos os papéis
    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // 🔹 Classe interna para receber JSON no Request
    public static class RoleRequest {
        private RoleName name;

        public RoleName getName() { return name; }
        public void setName(RoleName name) { this.name = name; }
    }
}
