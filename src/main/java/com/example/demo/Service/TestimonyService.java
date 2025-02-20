package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Testimony;
import com.example.demo.Repository.TestimonyRepository;

@Service
public class TestimonyService {


    @Autowired
    private TestimonyRepository TestimonyRepository;

    public String save(Testimony testimony) {
    
            try {
                if (testimony.getName() == null || testimony.getName().trim().isEmpty()) {
                    throw new IllegalArgumentException("O nome é obrigatório.");
                }
                if (testimony.getTestimony() == null || testimony.getTestimony().trim().isEmpty()) {
                    throw new IllegalArgumentException("A mensagem é obrigatória.");
                }
                TestimonyRepository.save(testimony);
                return "testimony salvo com sucesso!";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            } catch (Exception e) {
                return "Erro ao salvar o testimony: " + e.getMessage();
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
                throw new IllegalArgumentException("O nome do depoimento é obrigatório.");
            }
            if (!TestimonyRepository.existsById(id)) {
                throw new IllegalArgumentException("depoimento não encontrado.");
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


