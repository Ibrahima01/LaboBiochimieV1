package com.example.LaboBiochimie.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Repository.PatientRepository;
import com.example.LaboBiochimie.Service.PatientService;
import com.example.LaboBiochimie.enums.Roles;

@Service("PatientServiceImpl")
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	AppUserServiceImpl appUserService;

	@Override
	public void SavePatient(Patient patient) {
		AppUser user = patient.getUser();
		if (user != null) {
			user.setRole(Roles.PATIENT);
		}
		user = appUserService.SaveUser(patient.getUser());
		patient.setUser(user);
		patientRepository.save(patient);
	}

	@Override
	public void UpdatePatient(Long Id, Patient patient) {
		Optional<Patient> patient1 = patientRepository.findById(Id);
		if (patient1.isEmpty()) {
			System.out.println("Id inexistant");
		} else {
			Patient newPatient = patient1.get();
			newPatient.setNom_Patient(patient.getNom_Patient());
			newPatient.setPrenom_Patient(patient.getPrenom_Patient());
			// newPatient.setEmail(patient.getEmail());
			newPatient.setTel(patient.getTel());/*
												 * newPatient.setPhotoPatient(patient.getPhotoPatient());
												 */
			newPatient.setCommentaires(patient.getCommentaires());
			patientRepository.save(newPatient);
		}
	}

	@Override
	public List<Patient> ListPatients() {
		return patientRepository.findAll();
	}

	@Override
	public void RemovePatient(Long Id) {
		patientRepository.deleteById(Id);
	}

	@Override
	public Optional<Patient> findPatient(Long Id) {
		return patientRepository.findById(Id);
	}
}