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

    @SuppressWarnings("null")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Tour> findById(@PathVariable Long id) {
        try {
            Tour tour = tourService.findById(id);
            return new ResponseEntity<>(tour, HttpStatus.OK); 
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @SuppressWarnings("null")
    @GetMapping
    public ResponseEntity<List<Tour>> findAll() {
        try {
            List<Tour> tours = tourService.findAll();
            return new ResponseEntity<>(tours, HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<Tour> save(@RequestBody Tour tour) {
        try {
            Tour savedTour = tourService.save(tour);
            return new ResponseEntity<>(savedTour, HttpStatus.CREATED); 
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            tourService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @SuppressWarnings("null")
    @PutMapping
    public ResponseEntity<Tour> edit(@RequestBody Tour tour) {
        try {
            Tour updatedTour = tourService.update(tour);
            return new ResponseEntity<>(updatedTour, HttpStatus.OK); 
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
}
 