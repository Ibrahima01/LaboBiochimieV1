package com.example.LaboBiochimie.Entities;

public class Box {
    private int numeroBox;
    private int nombrePatient;
    private int tempRestant;

    public Box(int numeroBox, int nombrePatient, int tempRestant) {
        this.numeroBox = numeroBox;
        this.nombrePatient = nombrePatient;
        this.tempRestant = tempRestant;
    }
    public Box(){
        super();
    }

    public int getNumeroBox() {
        return numeroBox;
    }

    public void setNumeroBox(int numeroBox) {
        this.numeroBox = numeroBox;
    }

    public int getNombrePatient() {
        return nombrePatient;
    }

    public void setNombrePatient(int nombrePatient) {
        this.nombrePatient = nombrePatient;
    }

    public int getTempRestant() {
        return tempRestant;
    }

    public void setTempRestant(int tempRestant) {
        this.tempRestant = tempRestant;
    }
}
