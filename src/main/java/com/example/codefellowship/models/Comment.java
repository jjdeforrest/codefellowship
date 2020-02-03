package com.example.codefellowship.models;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.persistence.*;
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public String body;

    @ManyToOne
    public ApplicationUser applicationUser;

    public Comment(){};

    public Comment(String body){
        this.body = body;

    }



}