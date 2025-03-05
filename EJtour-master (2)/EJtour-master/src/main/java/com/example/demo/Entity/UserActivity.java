package main.java.com.example.demo.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_activities")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String action; 
    private String endpoint; 
    private LocalDateTime timestamp = LocalDateTime.now(); 

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}