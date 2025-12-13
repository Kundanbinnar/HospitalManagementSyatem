package com.kundan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kundan.entity.Patient;
import com.kundan.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepo;
	
	public Patient addPatient(Patient patient) {
		return patientRepo.save(patient);
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
			existingPatient.setBloodGroup(patient.getBloodGroup());
			existingPatient.setDob(patient.getDob());
			existingPatient.setGender(patient.getGender());
			existingPatient.setAddress(patient.getAddress());
			existingPatient.setPhoneNo(patient.getPhoneNo());
			existingPatient.setEmail(patient.getEmail());
			
			return patientRepo.save(existingPatient);
		}else {
			throw new IllegalArgumentException("Patient with id "+ id + "not found");
		}
	}
	
	public Patient searchByEmail(String email) {
		return patientRepo.searchByEmail(email);
	}
	
	public boolean existById(int id) {
		return patientRepo.existsById(id);
	}
	
	public Long patientCount() {
		return patientRepo.count();
	}

}
