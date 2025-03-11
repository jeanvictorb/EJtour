package com.example.demo.Entity;


import javax.xml.stream.events.Comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
 import jakarta.persistence.*;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.Setter;
 
 @Entity
 @Getter
 @Setter
 @NoArgsConstructor
 @JsonIgnoreProperties({"likes", "comments"})
 public class Like {
 
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
 
     @ManyToOne
     @JoinColumn(name = "comment_id", nullable = false)
     private Comment comment; 
 
     @ManyToOne
     @JoinColumn(name = "user_id", nullable = false)
     private User user; 
 
     public Like(Comment comment, User user) {
         this.comment = comment;
         this.user = user;
     }
 }