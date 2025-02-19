package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.Testimony;
import com.example.demo.Service.TestimonyService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/testimony")
public class TestimonyController {
    
    @Autowired
    private TestimonyService testimonyService;
    

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Testimony testimony) {
        try {
            String mensagem = testimonyService.save(testimony);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar o User.", HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Testimony testimony) {
        try {
            String mensagem = testimonyService.update(id, testimony);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar o User.", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            String mensagem = testimonyService.delete(id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar o User.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findall")
    public ResponseEntity<List<Testimony>> findAll() {
        try {
            List<Testimony> lista = testimonyService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Testimony> findById(@PathVariable long id) {
        try {
            Optional<Testimony> TestimonyService = testimonyService.findById(id);
            return TestimonyService.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
