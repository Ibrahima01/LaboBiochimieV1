package com.example.LaboBiochimie.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import com.example.LaboBiochimie.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Repository.PatientRepository;
import com.example.LaboBiochimie.Service.PatientService;
import com.example.LaboBiochimie.enums.Roles;

import javax.transaction.Transactional;

@Service("PatientServiceImpl")
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	AppUserServiceImpl appUserService;

	@Override
	@Transactional
	public void SavePatient(Patient patient) throws UserException {
		AppUser user = patient.getUser();
		if (user != null) {
			user.setRole(Roles.PATIENT.toString());
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

	@Override
	public Optional<Patient> findPatientByUser(AppUser user) {
		return patientRepository.findByUser(user);
	}
}
