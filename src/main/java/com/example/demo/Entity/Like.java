package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"comment", "user"})
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private String comment; 

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; 

    public Like(String comment, User user) {
        this.comment = comment;
        this.user = user;
    }
}
