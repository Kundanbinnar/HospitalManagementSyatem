package com.kundan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kundan.dto.LoginRequest;
import com.kundan.dto.LoginResponse;
import com.kundan.entity.Doctor;
import com.kundan.entity.Patient;
import com.kundan.entity.User;
import com.kundan.repository.DoctorRepository;
import com.kundan.repository.PatientRepository;
import com.kundan.repository.UserRepository;
import com.kundan.security.JwtService;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	
	public LoginResponse loginUser(LoginRequest loginRequest){
           Optional<User> user = userRepo.findByEmail(loginRequest.getEmail());
           
           if(user.isPresent()) {
        	   User existingUser = user.get();
        	  
        	   if (passwordEncoder.matches(loginRequest.getPassword(),existingUser.getPassword())) {
        		   
        		   String token = jwtService.generateToken(existingUser.getEmail());
        		   
        		   LoginResponse response = new LoginResponse();
        		   
        		   response.setToken(token);
        		   response.setRole(existingUser.getRole());
        		   
        		   
                   return response;
               }
           }
           return null;
	}
	
	
	public User registerUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepo.save(user);
		
		if(savedUser.getRole().equalsIgnoreCase("PATIENT")) {
			Patient patient = new Patient();
			patient.setUser(savedUser);
			patientRepo.save(patient);
		}
		
		if(savedUser.getRole().equalsIgnoreCase("DOCTOR")) {
			Doctor doctor = new Doctor();
			doctor.setUser(savedUser);
			doctorRepo.save(doctor);
		}
		
		return savedUser;
	}
		
	
}
