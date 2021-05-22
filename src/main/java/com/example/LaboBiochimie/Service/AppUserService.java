package com.example.LaboBiochimie.Service;

import com.example.LaboBiochimie.Entities.AppRole;
import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.exception.UserException;

public interface AppUserService {
	public AppUser SaveUser(AppUser appUser) throws UserException;

	public AppUser login(String email);

	public void UpdateUser(Long Id, AppUser user);

	public void RemoveUser(Long Id);

	public AppUser loadUserByusername(String username);

	public AppRole save(AppRole role);

	public void addRoleToUser(String username, String rolename);

	//public Patient findPatientByUsername(String username);
}
