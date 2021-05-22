package com.example.LaboBiochimie.dto;

import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Entities.Patient;
import lombok.NonNull;

import javax.persistence.OneToOne;

public class PatientDto {
    @NonNull
    private String nom_Patient;
    @NonNull
    private String prenom_Patient;
    private int age;
    private String sexe;
    private boolean obese;
    private boolean femme_enceinte;
    private String tel;
    private UserDto user;

    public PatientDto(){super();}

    public PatientDto(@NonNull String nom_Patient, @NonNull String prenom_Patient, int age, String sexe, boolean obese, boolean femme_enceinte, String tel, UserDto user) {
        this.nom_Patient = nom_Patient;
        this.prenom_Patient = prenom_Patient;
        this.age = age;
        this.sexe = sexe;
        this.obese = obese;
        this.femme_enceinte = femme_enceinte;
        this.tel = tel;
        this.user = user;
    }

    public Patient patientDtoToPatient(){
        Patient patient=new Patient();
        patient.setNom_Patient(this.nom_Patient);
        patient.setPrenom_Patient(this.prenom_Patient);
        patient.setAge(this.age);
        patient.setSexe(this.sexe);
        patient.setObese(this.obese);
        patient.setFemme_enceinte(this.femme_enceinte);
        patient.setTel(this.tel);
        patient.setUser(this.user.userToAppUser());
        return patient;
    }

    public String getNom_Patient() {
        return nom_Patient;
    }

    public void setNom_Patient(String nom_Patient) {
        this.nom_Patient = nom_Patient;
    }

    public String getPrenom_Patient() {
        return prenom_Patient;
    }

    public void setPrenom_Patient(String prenom_Patient) {
        this.prenom_Patient = prenom_Patient;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public boolean isObese() {
        return obese;
    }

    public void setObese(boolean obese) {
        this.obese = obese;
    }

    public boolean isFemme_enceinte() {
        return femme_enceinte;
    }

    public void setFemme_enceinte(boolean femme_enceinte) {
        this.femme_enceinte = femme_enceinte;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
