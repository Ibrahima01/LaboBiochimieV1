package com.example.LaboBiochimie.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Service.AppUserService;

@RestController
public class UserController {
	@Autowired
	private AppUserService accountService;

	@GetMapping("/user")
	public AppUser getUser(Principal p) {
		return accountService.loadUserByusername(p.getName());
	}
}
