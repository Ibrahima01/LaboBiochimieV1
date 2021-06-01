package com.example.LaboBiochimie.Controller;

import java.util.List;
import java.util.Optional;

import com.example.LaboBiochimie.dto.PatientDto;
import com.example.LaboBiochimie.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Service.PatientService;

@RestController
@RequestMapping("Patient")
@CrossOrigin("*")
public class PatientController {
	@Autowired
	PatientService patientService;

	@PostMapping("/newPatient")
	public Patient addNewPatient(@Validated @RequestBody PatientDto p1) throws UserException {
		patientService.SavePatient(p1.patientDtoToPatient());
		return p1.patientDtoToPatient();
	}

	@PutMapping("/Patient/{id}")
	public void modifyPatient(@PathVariable Long id, @Validated @RequestBody Patient p1) {
		patientService.UpdatePatient(id, p1);
	}

	/*
	 * @GetMapping(value = "/authentification/{email, password}") public boolean
	 * authentifier(@PathVariable (value = "email") String email, @PathVariable
	 * (value="password") String password){return
	 * patientService.authentification(email,password);}
	 */
	@GetMapping("/GetAllPatients")
	public List<Patient> listAdmin() {
		return patientService.ListPatients();
	}

	@GetMapping(value = "/findPatientById/{id}")
	public Optional<Patient> getPatientById(@PathVariable(value = "id") String id) {
		return patientService.findPatient(Long.parseLong(id));
	}

	@PostMapping(value = "/deletePatient/{id}")
	public void deletePatient(@PathVariable Long id) {
		patientService.RemovePatient(id);
	}
}
