package com.kundan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kundan.entity.User;
import com.kundan.repository.UserRepository;

import io.jsonwebtoken.lang.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	 @Autowired
	    private UserRepository userRepository;

	 @Override
	 public UserDetails loadUserByUsername(String email)
	         throws UsernameNotFoundException {

	     User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

	     return org.springframework.security.core.userdetails.User
	             .withUsername(user.getEmail())
	             .password(user.getPassword())
	             .roles(user.getRole())
	             .build();
	 }

}
