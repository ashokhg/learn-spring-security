package com.demo.springssecurity.service;

import java.util.List;

import com.demo.springssecurity.entities.Users;

public interface UserService {

	public Users addUser(Users user);
	
	public Users getUserById(int id);
	
}
