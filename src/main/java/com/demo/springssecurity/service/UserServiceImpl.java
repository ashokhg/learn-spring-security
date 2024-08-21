package com.demo.springssecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.springssecurity.dao.UserRepo;
import com.demo.springssecurity.entities.Users;

@Service
public class UserServiceImpl implements UserService {
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@Autowired
	UserRepo userRepo;

	@Override
	public Users addUser(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return this.userRepo.save(user);
	}

	@Override
	public Users getUserById(int id) {
		return this.userRepo.getById(id);
	}

}
