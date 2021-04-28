package com.example.LaboBiochimie.sec;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Service.AppUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AppUserService appUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserService.loadUserByusername(username);
		if (appUser == null)
			throw new UsernameNotFoundException("invalid user");
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(appUser.getRole().toString()));
		return new User(appUser.getUsername(), appUser.getPassword(), authorities);
	}
}
