package com.example.LaboBiochimie.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Personnel")
public class RendezVous implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_RDV;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime date_heure_RDV;
    @NonNull
    private int numero_box;

    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName="id")
    private Patient RDVPatient;

    @ManyToOne
    @JoinColumn(name="personnel_id", referencedColumnName="id")
    private Personnel RDVPersonnel;

    @ManyToOne
    @JoinColumn(name="Admin_id", referencedColumnName="id")
    private Admin RDVAdmin;

    public int getId_RDV() {
        return id_RDV;
    }

    public LocalDateTime getDate_heure_RDV() {
        return date_heure_RDV;
    }

    public void setDate_heure_RDV(LocalDateTime date_heure_RDV) {
        this.date_heure_RDV = date_heure_RDV;
    }

    public int getNumero_box() {
        return numero_box;
    }

    public void setNumero_box(int numero_box) {
        this.numero_box = numero_box;
    }

    public Patient getRDVPatient() {
        return RDVPatient;
    }

    public void setRDVPatient(Patient RDVPatient) {
        this.RDVPatient = RDVPatient;
    }

    public Personnel getRDVPersonnel() {
        return RDVPersonnel;
    }

    public void setRDVPersonnel(Personnel RDVPersonnel) {
        this.RDVPersonnel = RDVPersonnel;
    }

    public Admin getRDVAdmin() {
        return RDVAdmin;
    }

    public void setRDVAdmin(Admin RDVAdmin) {
        this.RDVAdmin = RDVAdmin;
    }
}
