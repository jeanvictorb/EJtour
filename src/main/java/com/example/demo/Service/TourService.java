package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Tour;
import com.example.demo.Repository.TourRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public List<Tour> findAll() {
        try {
            return tourRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar os passeios: " + e.getMessage());
        }
    }

    public Tour findById(Long id) {
        try {
            Optional<Tour> tour = tourRepository.findById(id);
            if (tour.isEmpty()) {
                throw new RuntimeException("Passeio não encontrado!");
            }
            return tour.get();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o passeio: " + e.getMessage());
        }
    }

    public Tour save(Tour tour) {
        try {
            return tourRepository.save(tour);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o passeio: " + e.getMessage());
        }
    }

    public ResponseEntity<Void> delete(Long id) {
        try {
            if (!tourRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            tourRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o passeio: " + e.getMessage());
        }
    }

    public Tour update(Tour tour) {
        try {
            if (tour.getId() == null || !tourRepository.existsById(tour.getId())) {
                throw new RuntimeException("Passeio não encontrado para atualização.");
            }
            return tourRepository.save(tour);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o passeio: " + e.getMessage());
        }
    }

    public List<Tour> findByDestination(String destination) {
        try {
            return tourRepository.findByDestination(destination);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar passeios por destino: " + e.getMessage());
        }
    }

    public List<Tour> findByPriceRange(Double minPrice, Double maxPrice) {
        try {
            return tourRepository.findByPriceBetween(minPrice, maxPrice);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar passeios por intervalo de preço: " + e.getMessage());
        }
    }
}
