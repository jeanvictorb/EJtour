package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Tour;
import com.example.demo.Service.TourService;

import java.util.List;

@RestController
@RequestMapping(value = "/tour")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tour> findById(@PathVariable Long id) {
        try {
            Tour tour = tourService.findById(id);
            return new ResponseEntity<>(tour, HttpStatus.OK); // Status 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Status 404 - Não encontrado
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Status 500 - Erro interno
        }
    }

    @GetMapping
    public ResponseEntity<List<Tour>> findAll() {
        try {
            List<Tour> tours = tourService.findAll();
            return new ResponseEntity<>(tours, HttpStatus.OK); // Status 200
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Status 500 - Erro interno
        }
    }

    @PostMapping
    public ResponseEntity<Tour> save(@RequestBody Tour tour) {
        try {
            Tour savedTour = tourService.save(tour);
            return new ResponseEntity<>(savedTour, HttpStatus.CREATED); // Status 201 - Criado
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Status 400 - Bad Request
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            tourService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Status 204 - Sem conteúdo
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Status 404 - Não encontrado
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Status 500 - Erro interno
        }
    }

    @PutMapping
    public ResponseEntity<Tour> edit(@RequestBody Tour tour) {
        try {
            Tour updatedTour = tourService.update(tour);
            return new ResponseEntity<>(updatedTour, HttpStatus.OK); // Status 200 - Ok
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Status 404 - Não encontrado
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Status 500 - Erro interno
        }
    }
}
