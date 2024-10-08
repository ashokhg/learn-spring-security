package com.demo.springssecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.springssecurity.dao.UserPrincipal;
import com.demo.springssecurity.dao.UserRepo;
import com.demo.springssecurity.entities.Users;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userRepo.findByName(username);
		
		if(user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User 404");
		}
		
		return new UserPrincipal(user);
	}

}
