package com.example.demo.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String save(User user){
        try {
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            
            if (existingUser.isPresent()) {
                throw new RuntimeException("Email já cadastrado!");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
            return "Usuário cadastrado com sucesso!";
        } catch (RuntimeException e) {
            System.err.println(user);
            return "Erro ao registrar usuário: " + e.getMessage();
        } catch (Exception e) {
            System.err.println(user);

            return "Erro inesperado ao registrar usuário: " + e.getMessage();
        }

    }

    public String delete(long id) {
        try {
            userRepository.deleteById(id);
            return "usuario deletado com sucesso!";
        } catch (EmptyResultDataAccessException e) {
            return "Usuario não encontrado.";
        } catch (Exception e) {
            return "Erro ao deletar o Usuario: " + e.getMessage();
        }
    }
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
    public String update(long id, User user) {
        try {
            if (user.getName() == null || user.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do User é obrigatório.");
            }
            if (!userRepository.existsById(id)) {
                throw new IllegalArgumentException("User não encontrado.");
            }
            user.setId(id);
            userRepository.save(user);
            return "Atualização realizada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar o User: " + e.getMessage();
        }
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
