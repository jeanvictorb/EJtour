package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.Entity.Testimony;
import com.example.demo.Repository.TestimonyRepository;

public class TestimonyService {


    @Autowired
    private TestimonyRepository TestimonyRepository;

    public String save(Testimony testimony) {
        try {
            if (testimony.getName() == null || testimony.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do usuario é obrigatório.");
            }
            TestimonyRepository.save(testimony);
            return "Usuario salvo com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao salvar o Usuario: " + e.getMessage();
        }
    }
    public String delete(long id) {
        try {
            TestimonyRepository.deleteById(id);
            return "usuario deletado com sucesso!";
        } catch (EmptyResultDataAccessException e) {
            return "Usuario não encontrado.";
        } catch (Exception e) {
            return "Erro ao deletar o Usuario: " + e.getMessage();
        }
    }
    public Optional<Testimony> findById(long id) {
        return TestimonyRepository.findById(id);
    }
    public String update(long id, Testimony testimony) {
        try {
            if (testimony.getName() == null || testimony.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do Testimony é obrigatório.");
            }
            if (!TestimonyRepository.existsById(id)) {
                throw new IllegalArgumentException("Testimony não encontrado.");
            }
            testimony.setId(id);
            TestimonyRepository.save(testimony);
            return "Atualização realizada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar o Testimony: " + e.getMessage();
        }
    }
    public List<Testimony> findAll() {
        return TestimonyRepository.findAll();
    }
}


