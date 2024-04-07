package com.chatop.ChaTopApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rentals rental;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users user;


    @Column(length = 2000)
    private String message;

    private Date created_at;

    private Date updated_at;


    @PrePersist
    public void onCreate() {
        this.created_at = new Date();
        this.updated_at = new Date();
    }
    @PreUpdate
    public void onUpdate() {
        this.updated_at = new Date();
    }
}
