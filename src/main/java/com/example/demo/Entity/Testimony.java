package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Testimony {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "O campo nome é obrigatório.")
    private String name;
    
    @NotBlank(message = "O campo testemunho é obrigatório.")
    private String testimony;
    
    @ManyToOne 
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("testimonies")
    private User user; 
}
