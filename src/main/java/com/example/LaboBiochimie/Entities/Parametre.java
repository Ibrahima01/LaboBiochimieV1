package com.example.LaboBiochimie.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parametre {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int NOMBRE_BOX;
    private int VOLUME_HORRAIRE_PAR_BOX;
    private int HEURE_DEBUT;
    private int HEURE_FIN;
    private int QUOTAS;
    private int DUREE_PRELEVEMENT_AGE_0_2;
    private int DUREE_PRELEVEMENT_AGE_2_12;
    private int DUREE_PRELEVEMENT_AGE_PLUS12;
    private int DUREE_PRELEVEMENT_OBESE;

    public Parametre() {
        this.NOMBRE_BOX = 4;
        this.VOLUME_HORRAIRE_PAR_BOX = 150;
        this.HEURE_DEBUT = 8;
        this.HEURE_FIN = 14;
        this.QUOTAS = 40;
        this.DUREE_PRELEVEMENT_AGE_0_2 = 15;
        this.DUREE_PRELEVEMENT_AGE_2_12 = 10;
        this.DUREE_PRELEVEMENT_AGE_PLUS12 = 3;
        this.DUREE_PRELEVEMENT_OBESE = 5;
    }

    public int getNOMBRE_BOX() {
        return NOMBRE_BOX;
    }

    public void setNOMBRE_BOX(int NOMBRE_BOX) {
        this.NOMBRE_BOX = NOMBRE_BOX;
    }

    public int getVOLUME_HORRAIRE_PAR_BOX() {
        return VOLUME_HORRAIRE_PAR_BOX;
    }

    public void setVOLUME_HORRAIRE_PAR_BOX(int VOLUME_HORRAIRE_PAR_BOX) {
        this.VOLUME_HORRAIRE_PAR_BOX = VOLUME_HORRAIRE_PAR_BOX;
    }

    public int getHEURE_DEBUT() {
        return HEURE_DEBUT;
    }

    public void setHEURE_DEBUT(int HEURE_DEBUT) {
        this.HEURE_DEBUT = HEURE_DEBUT;
    }

    public int getHEURE_FIN() {
        return HEURE_FIN;
    }

    public void setHEURE_FIN(int HEURE_FIN) {
        this.HEURE_FIN = HEURE_FIN;
    }

    public int getQUOTAS() {
        return QUOTAS;
    }

    public void setQUOTAS(int QUOTAS) {
        this.QUOTAS = QUOTAS;
    }

    public int getDUREE_PRELEVEMENT_AGE_0_2() {
        return DUREE_PRELEVEMENT_AGE_0_2;
    }

    public void setDUREE_PRELEVEMENT_AGE_0_2(int DUREE_PRELEVEMENT_AGE_0_2) {
        this.DUREE_PRELEVEMENT_AGE_0_2 = DUREE_PRELEVEMENT_AGE_0_2;
    }

    public int getDUREE_PRELEVEMENT_AGE_2_12() {
        return DUREE_PRELEVEMENT_AGE_2_12;
    }

    public void setDUREE_PRELEVEMENT_AGE_2_12(int DUREE_PRELEVEMENT_AGE_2_12) {
        this.DUREE_PRELEVEMENT_AGE_2_12 = DUREE_PRELEVEMENT_AGE_2_12;
    }

    public int getDUREE_PRELEVEMENT_AGE_PLUS12() {
        return DUREE_PRELEVEMENT_AGE_PLUS12;
    }

    public void setDUREE_PRELEVEMENT_AGE_PLUS12(int DUREE_PRELEVEMENT_AGE_PLUS12) {
        this.DUREE_PRELEVEMENT_AGE_PLUS12 = DUREE_PRELEVEMENT_AGE_PLUS12;
    }

    public int getDUREE_PRELEVEMENT_OBESE() {
        return DUREE_PRELEVEMENT_OBESE;
    }

    public void setDUREE_PRELEVEMENT_OBESE(int DUREE_PRELEVEMENT_OBESE) {
        this.DUREE_PRELEVEMENT_OBESE = DUREE_PRELEVEMENT_OBESE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
