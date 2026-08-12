package com.kundan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kundan.dto.LoginRequest;
import com.kundan.entity.Doctor;
import com.kundan.entity.Patient;
import com.kundan.entity.User;
import com.kundan.repository.DoctorRepository;
import com.kundan.repository.PatientRepository;
import com.kundan.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	
	public User loginUser(LoginRequest loginRequest){
		return userRepo.findByUserNameAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
	}
	
	
	public User registerUser(User user) {
		
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
