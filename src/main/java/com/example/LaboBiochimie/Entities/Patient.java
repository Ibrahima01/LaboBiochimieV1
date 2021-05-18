package com.example.LaboBiochimie.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.NonNull;

@Entity
@Table(name = "Patient")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NonNull
	private String nom_Patient;
	@NonNull
	private String prenom_Patient;
	private int age;
	private String sexe;
	private boolean obese;
	private boolean femme_enceinte;
	private String tel;
	// @Lob
	// private byte[] photoPatient;
	@OneToOne
	private AppUser user;

	// @OneToMany (mappedBy = "RDVPatient", cascade = CascadeType.ALL)
	// private Set<Rendez_vous> Rendez_vous_patient=new HashSet<>();

	public Long getId() {
		return id;
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

	/*
	 * public User getUser() { return user; } public void setUser(User user) {
	 * this.user = user; }
	 */

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	/*
	 * public Set<Rendez_vous> getRendez_vous_patient() { return
	 * Rendez_vous_patient; } public void setRendez_vous_patient(Set<Rendez_vous>
	 * rendez_vous_patient) { Rendez_vous_patient = rendez_vous_patient; }
	 */

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser appUser) {
		this.user = appUser;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isObese() {
		return obese;
	}

	public void setObese(boolean obese) {
		this.obese = obese;
	}

	public Patient() {
		super();
	}

	public boolean isFemme_enceinte() {
		return femme_enceinte;
	}

	public void setFemme_enceinte(boolean femme_enceinte) {
		this.femme_enceinte = femme_enceinte;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
}
