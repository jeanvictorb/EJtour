package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AuthRequest;
import com.example.demo.Dto.ResgisterRequest;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Security.JwtUtil;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    private final PasswordEncoder passwordEncorder;
    private final JwtUtil jwtUtil; 

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncorder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncorder = passwordEncorder;
        this.jwtUtil = jwtUtil;
    }

    public String register(ResgisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail()); // Corrigido nome do método
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email já cadastrado!"); // Corrigido RuntimeErrorException para RuntimeException
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncorder.encode(request.getPassword())); // Correção no encoding da senha

        userRepository.save(user);
        return "Usuário cadastrado com sucesso!";
    }

    public String login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail()) // Corrigido nome do método
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!passwordEncorder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha incorreta!");
        }

        return jwtUtil.generateToken(user.getEmail()); // Removido `return null;` desnecessário
    }

    public String save(User user) {
        try {
            if (user.getName() == null || user.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do usuário é obrigatório.");
            }
            userRepository.save(user);
            return "Usuário salvo com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao salvar o usuário: " + e.getMessage();
        }
    }

    public String delete(long id) {
        try {
            userRepository.deleteById(id);
            return "Usuário deletado com sucesso!";
        } catch (EmptyResultDataAccessException e) {
            return "Usuário não encontrado.";
        } catch (Exception e) {
            return "Erro ao deletar o usuário: " + e.getMessage();
        }
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public String update(long id, User user) {
        try {
            if (user.getName() == null || user.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do usuário é obrigatório.");
            }
            if (!userRepository.existsById(id)) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setId(id);
            userRepository.save(user);
            return "Atualização realizada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar o usuário: " + e.getMessage();
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
