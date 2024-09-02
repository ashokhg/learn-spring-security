package com.demo.springssecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springssecurity.dto.UserDto;
import com.demo.springssecurity.entities.Users;
import com.demo.springssecurity.service.JwtServiceImpl;
import com.demo.springssecurity.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtServiceImpl jwtService;
	
	@GetMapping("/")
	public ResponseEntity<String> index(){
		return ResponseEntity.ok("Index");
	}
	
	@GetMapping("/home")
	public String home(HttpServletRequest request){
		
		return "Home page : Session id :" + request.getSession().getId();
	}
	
	@GetMapping("/index")
	public String index(HttpServletRequest request){
		
		return "Index page : Session id :" + request.getSession().getId();
	}
	
	@PostMapping("/register-user")
	public ResponseEntity<Users> addUsers(@RequestBody Users user){
		
		Users user1 = userService.addUser(user);
		
		return ResponseEntity.ok(user1);
	}
	
	@GetMapping("/get-user/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id){
		System.out.println("in get users controller");
		UserDto user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		
		System.out.println("In login controller");
		Authentication authentication = authenticationManager
												.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			System.out.println("User Authenticated ");
			return jwtService.generateToken(user.getName());
		} else {
			return "failed";
		}
		
	}
	
}
