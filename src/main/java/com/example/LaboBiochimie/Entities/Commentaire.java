package com.example.LaboBiochimie.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Commentaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String commentaire;
    private ArrayList<String> reponse;
    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName="id")
    private Patient patient_commentaire;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public ArrayList<String> getReponse() {
        return reponse;
    }

    public void setReponse(ArrayList<String> reponse) {
        this.reponse = reponse;
    }

    public Patient getPatient_commentaire() {
        return patient_commentaire;
    }

    public void setPatient_commentaire(Patient patient_commentaire) {
        this.patient_commentaire = patient_commentaire;
    }
}
