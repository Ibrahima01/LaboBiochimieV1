package com.example.LaboBiochimie.Entities;

import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Personnel")
public class Personnel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String nom_personnel;
    @NonNull
    private String prenom_personnel;
    @NonNull
    private String email;
    @NonNull
    private String hopitalOrigine;
    private String gouvernorat;
    /*
    @Lob
    private byte[] photoPersonnel;
    */
    @OneToMany (mappedBy ="RDVPersonnel",cascade = CascadeType.ALL)
    private Set<RendezVous> Rendez_vous_personnel=new HashSet<>();
    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private AppUser appUser;

    public long getId() {
        return id;
    }

    public String getNom_personnel() {
        return nom_personnel;
    }

    public void setNom_personnel(String nom_personnel) {
        this.nom_personnel = nom_personnel;
    }

    public String getPrenom_personnel() {
        return prenom_personnel;
    }

    public void setPrenom_personnel(String prenom_personnel) {
        this.prenom_personnel = prenom_personnel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHopitalOrigine() {
        return hopitalOrigine;
    }

    public void setHopitalOrigine(String hopitalOrigine) {
        this.hopitalOrigine = hopitalOrigine;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public Set<RendezVous> getRendez_vous_personnel() {
        return Rendez_vous_personnel;
    }

    public void setRendez_vous_personnel(Set<RendezVous> rendez_vous_personnel) {
        Rendez_vous_personnel = rendez_vous_personnel;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
