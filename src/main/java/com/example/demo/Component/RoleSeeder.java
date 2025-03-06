package com.example.demo.Component;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.Role;
import com.example.demo.Repository.RoleRepository;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName(Role.ROLE_USER).isEmpty()) {
            roleRepository.save(new Role(null, Role.ROLE_USER, new HashSet<>()));
        }
        if (roleRepository.findByName(Role.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(new Role(null, Role.ROLE_ADMIN, new HashSet<>()));
        }
        if (roleRepository.findByName(Role.ROLE_MODERATOR).isEmpty()) {
            roleRepository.save(new Role(null, Role.ROLE_MODERATOR, new HashSet<>()));
        }
    }
}
