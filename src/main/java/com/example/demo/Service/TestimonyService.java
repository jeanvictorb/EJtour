package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Testimony;
import com.example.demo.Entity.User;
import com.example.demo.Repository.TestimonyRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class TestimonyService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestimonyRepository testimonyRepository;

    public TestimonyService(UserRepository userRepository, TestimonyRepository testimonyRepository) {
        this.userRepository = userRepository;
        this.testimonyRepository = testimonyRepository;
    }
    public Testimony save(Testimony testimony) {
        User user = userRepository.findByName(testimony.getName())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        testimony.setUser(user); 
        return testimonyRepository.save(testimony);
    }
    
    public String delete(long id) {
        try {
            testimonyRepository.deleteById(id);
            return "usuario deletado com sucesso!";
        } catch (EmptyResultDataAccessException e) {
            return "Usuario não encontrado.";
        } catch (Exception e) {
            return "Erro ao deletar o Usuario: " + e.getMessage();
        }
    }
    public Optional<Testimony> findById(long id) {
        return testimonyRepository.findById(id);
    }
    public String update(long id, Testimony testimony) {
        try {
            if (testimony.getName() == null || testimony.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do Testimony é obrigatório.");
            }
            if (!testimonyRepository.existsById(id)) {
                throw new IllegalArgumentException("Testimony não encontrado.");
            }
            testimony.setId(id);
            testimonyRepository.save(testimony);
            return "Atualização realizada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar o Testimony: " + e.getMessage();
        }
    }
    public List<Testimony> findAll() {
        return testimonyRepository.findAll();
    }
}


