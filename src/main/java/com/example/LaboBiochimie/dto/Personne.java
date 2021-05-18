package com.example.LaboBiochimie.dto;

import com.example.LaboBiochimie.Entities.AppUser;

public class Personne {
    private String nom;
    private String prenom;
    private AppUser  user;

    public  Personne(){super();}

    public Personne(String nom, String prenom, AppUser user) {
        this.nom = nom;
        this.prenom = prenom;
        this.user = user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
