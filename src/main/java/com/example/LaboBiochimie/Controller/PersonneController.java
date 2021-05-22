package com.example.LaboBiochimie.Controller;

import java.security.Principal;
import java.util.Optional;

import com.example.LaboBiochimie.Entities.Admin;
import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Service.AdminService;
import com.example.LaboBiochimie.Service.PatientService;
import com.example.LaboBiochimie.dto.PersonneDto;
import com.example.LaboBiochimie.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Service.AppUserService;

@RestController
public class PersonneController {
	@Autowired
	private AppUserService accountService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private AdminService adminService;
	@GetMapping("/personne")
	public PersonneDto getUser(Principal p) {
		PersonneDto personne = new PersonneDto();
		AppUser user = accountService.loadUserByusername(p.getName());
		personne.setUser(user);
		if(user.getRole().equals(Roles.PATIENT.toString())){
			Optional<Patient> patient = patientService.findPatientByUser(user);
			if(!patient.isEmpty()){
				personne.setNom(patient.get().getNom_Patient());
				personne.setPrenom(patient.get().getPrenom_Patient());
			}
		} else if(user.getRole().equals(Roles.ADMIN.toString())){
			Optional<Admin> admin = adminService.findAdminByUser(user);
			if(!admin.isEmpty()){
				personne.setNom(admin.get().getNom());
				personne.setPrenom(admin.get().getPrenom());
			}
		}
		return personne;
	}

	//@GetMapping("/user/findPatientByUsername/{username}")
    //public Patient getPatientByIdUser(String username){ return accountService.findPatientByUsername(username);}

}
