package com.kundan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kundan.dto.LoginRequest;
import com.kundan.entity.User;
import com.kundan.repository.UserRepository;
import com.kundan.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
		userService.loginUser(loginRequest);
		return ResponseEntity.ok("User Login in succesffully ");
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user){
		userService.registerUser(user);
		return ResponseEntity.ok("Regiseterd User successfully");
	}

}
