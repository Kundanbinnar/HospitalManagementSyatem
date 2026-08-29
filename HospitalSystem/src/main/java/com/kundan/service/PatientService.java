package com.kundan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kundan.entity.Patient;
import com.kundan.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepo;
	
	public Patient addPatient(int userId, Patient patient) {
		
		Patient patient1 = patientRepo.findByUser_Id(userId);
		
		if(patient1 == null) {
			throw new RuntimeException("Patiend not found for userId");
		}else {
			
			patient1.setName(patient.getName());
			patient1.setGender(patient.getGender());
			patient1.setAddress(patient.getAddress());
			patient1.setPhoneNo(patient.getPhoneNo());
			patient1.setDob(patient.getDob());
			patient1.setBloodGroup(patient.getBloodGroup());
			
			return patientRepo.save(patient1);
		}
		
	}
	
	public List<Patient> getAllPatient(){
		return patientRepo.findAll();
	}
	
	public Optional<Patient> getPatientById(int id) {
		return patientRepo.findById(id);
	}
	
	public void deletePatient(int id) {
		 patientRepo.deleteById(id);
	}
	
	public Patient updatePatientById(int id, Patient patient) {
		Optional<Patient> patient1 = patientRepo.findById(id);
		
		if(patient1.isPresent()) {
			Patient existingPatient = patient1.get();
			existingPatient.setName(patient.getName());
			existingPatient.setEmailId(patient.getEmailId());
			existingPatient.setBloodGroup(patient.getBloodGroup());
			existingPatient.setDob(patient.getDob());
			existingPatient.setGender(patient.getGender());
			existingPatient.setAddress(patient.getAddress());
			existingPatient.setPhoneNo(patient.getPhoneNo());
			
			return patientRepo.save(existingPatient);
		}else {
			throw new IllegalArgumentException("Patient with id "+ id + "not found");
		}
	}
	
	/*
	 * public Patient searchByEmail(String email) { return
	 * patientRepo.searchByEmail(email); }
	 */
	

	public boolean existById(int id) {
		return patientRepo.existsById(id);
	}
		
	public Long patientCount() { 
	   return patientRepo.count(); 
	}

	public Patient myProfile(String email) {	
		return patientRepo.findByEmailId(email);	
	}
	
	
	public Patient updateMyProfile(String email, Patient patient) {
		
		Patient newPatient = patientRepo.findByEmailId(email);
		
		if(newPatient != null ) {
			
		  newPatient.setDob(patient.getDob());
		  newPatient.setAddress(patient.getAddress());
		  newPatient.setBloodGroup(patient.getBloodGroup());
		  newPatient.setPhoneNo(patient.getPhoneNo());
		  
		  return patientRepo.save(newPatient);
		 
		}else {
			throw new IllegalArgumentException("Patient with id "+ email + "not found");
		}
	}

}
