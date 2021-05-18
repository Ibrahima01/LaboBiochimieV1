package com.example.LaboBiochimie.Service;

import com.example.LaboBiochimie.Entities.AppRole;
import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Entities.Patient;

public interface AppUserService {
	public AppUser SaveUser(AppUser appUser);

	public AppUser login(String email);

	public void UpdateUser(Long Id, AppUser user);

	public void RemoveUser(Long Id);

	public AppUser loadUserByusername(String username);

	public AppRole save(AppRole role);

	public void addRoleToUser(String username, String rolename);

	//public Patient findPatientByUsername(String username);
}
