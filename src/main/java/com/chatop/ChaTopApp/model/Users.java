package com.chatop.ChaTopApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;

    private Date created_at;

    private Date updated_at;

    @PrePersist //avant la sauvegarde dans la BDD pendant la création
    public void onCreate() {
        this.created_at = new Date(); //la date se créera automatiquement avant la sauvegarde dans la BDD
        this.updated_at = new Date();
    }

    @PreUpdate //avant la sauvegarde dans la BDD pendant la MAJ
    public void onUpdate() {
        this.updated_at = new Date();
    }

}
