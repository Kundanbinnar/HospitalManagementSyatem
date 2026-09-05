package com.kundan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kundan.entity.Doctor;
import com.kundan.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	public Doctor addDoctor(Doctor doctor){
		return doctorRepo.save(doctor);
	}
	
	public List<Doctor> getAllDoctors(){
		return doctorRepo.findAll();
	}
	
	public Optional<Doctor> getDoctorByID(int id) {
		return doctorRepo.findById(id);
	}
	
	public void deleteDoctor(int id) {
		doctorRepo.deleteById(id);
	}
	
	public void updateDoctorByID(int id, Doctor doctor) {
		 Optional<Doctor> doctor1 = doctorRepo.findById(id);
		 
		 if(doctor1.isPresent()){
			 Doctor existingDoctor = doctor1.get();
			 existingDoctor.setName(doctor.getName());
			 existingDoctor.setGender(doctor.getGender());
			 existingDoctor.setExperience(doctor.getExperience());
			 existingDoctor.setSpecialization(doctor.getSpecialization());
			 existingDoctor.setStatus(doctor.getStatus());
			 existingDoctor.setConsultationFee(doctor.getConsultationFee());
			 existingDoctor.setEmail(doctor.getEmail());
			 existingDoctor.setQualification(doctor.getQualification());
			 existingDoctor.setAbout(doctor.getAbout());
			 
			 
			 doctorRepo.save(existingDoctor);
		 }
		
	}
	
	public void updateDoctorStatus(int id, String status) {
        Optional<Doctor> doctor1 = doctorRepo.findById(id);
		 
		 if(doctor1.isPresent()) {
			 Doctor updatedStatus = doctor1.get();
			 updatedStatus.setStatus(status);
			 
			 doctorRepo.save(updatedStatus);
		 }
		 
	}
	
	public List<Doctor> getDoctorBySpecialization(String Specialization){
		
		return doctorRepo.findBySpecialization(Specialization);
	}
	
	public Doctor getMyProfile(String email) {
		return doctorRepo.findByEmail(email);
	}
	
	
	public Doctor updateMyProfile(String email, Doctor doctor) {
		
		Doctor existingDoctor = doctorRepo.findByEmail(email);
		if(existingDoctor != null) {
			existingDoctor.setAbout(doctor.getAbout());
			existingDoctor.setConsultationFee(doctor.getConsultationFee());
			existingDoctor.setExperience(doctor.getExperience());
			existingDoctor.setSpecialization(doctor.getSpecialization());
			existingDoctor.setGender(doctor.getGender());
			existingDoctor.setStatus(doctor.getStatus());
			
			return doctorRepo.save(existingDoctor);
		}else {
			throw new IllegalArgumentException("Doctor with id "+ email + "not found");
		}
	}

}
