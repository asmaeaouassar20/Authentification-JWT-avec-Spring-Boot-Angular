package com.example.demo.dto;

public class UserResponse {
    private Long id;
    private String email;
    private String nom;

    public UserResponse(Long id, String email, String nom) {
        this.id = id;
        this.email = email;
        this.nom = nom;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
