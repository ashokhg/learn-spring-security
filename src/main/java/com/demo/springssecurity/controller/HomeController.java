package com.demo.springssecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springssecurity.entities.Users;
import com.demo.springssecurity.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class HomeController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<String> index(){
		return ResponseEntity.ok("Index");
	}
	
	@GetMapping("/home")
	public String home(HttpServletRequest request){
		
		return "Session id :" + request.getSession().getId();
	}
	
	@PostMapping("/add-users")
	public ResponseEntity<Users> addUsers(@RequestBody Users user){
		
		Users user1 = userService.addUser(user);
		
		return ResponseEntity.ok(user1);
	}
}
