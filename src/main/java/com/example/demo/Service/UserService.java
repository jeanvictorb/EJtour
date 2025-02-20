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
        try {
            Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
            
            // Verifica se o email já está cadastrado
            existingUser.orElseThrow(() -> new RuntimeException("Email já cadastrado!"));
    
            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncorder.encode(request.getPassword()));
    
            userRepository.save(user);
            return "Usuário cadastrado com sucesso!";
            
        } catch (RuntimeException e) {
            return "Erro ao registrar usuário: " + e.getMessage();
        } catch (Exception e) {
            return "Erro inesperado ao registrar usuário: " + e.getMessage();
        }
    }
    
    public String login(AuthRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    
            // Verifica se a senha está correta
            if (!passwordEncorder.matches(request.getPassword(), user.getPassword())) {
                throw new RuntimeException("Senha incorreta!");
            }
    
            return jwtUtil.generateToken(user.getEmail());
    
        } catch (RuntimeException e) {
            return "Erro ao fazer login: " + e.getMessage();
        } catch (Exception e) {
            return "Erro inesperado ao fazer login: " + e.getMessage();
        }
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
