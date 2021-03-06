package com.example.LaboBiochimie.ServiceImplementation;

import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Repository.PatientRepository;
import com.example.LaboBiochimie.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LaboBiochimie.Entities.AppRole;
import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Repository.AppRoleRepository;
import com.example.LaboBiochimie.Repository.AppUserRepository;
import com.example.LaboBiochimie.Service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	AppRoleRepository appRoleRepository;
	@Autowired
	PatientRepository patientRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public AppUser SaveUser(AppUser appUser) throws UserException {
		AppUser user = appUserRepository.findByUsername(appUser.getUsername());
		if (user != null)
			throw new UserException(appUser.getUsername() + " existe dèjà");
		appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
		appUser.setActived(true);
		appUserRepository.save(appUser);

		return appUser;
	}

	@Override
	public AppUser login(String email) {
		return null;
	}

	@Override
	public void UpdateUser(Long Id, AppUser user) {

	}

	@Override
	public void RemoveUser(Long Id) {

	}

	@Override
	public AppUser loadUserByusername(String username) {
		return appUserRepository.findByUsername(username);
	}

	@Override
	public AppRole save(AppRole role) {
		return appRoleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findByRoleName(rolename);
	}

	/*
	@Override
	public Patient findPatientByUsername(String username){
	AppUser appuser=appUserRepository.findByUsername(username);
	Patient patient=appuser.getPatient();
	for (Patient p:patientRepository.findAll()){
		if(p.getId()==patient.getId())
			patient=p;
	}
	return patient;}
	 */
}