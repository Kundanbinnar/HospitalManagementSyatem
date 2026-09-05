package com.kundan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kundan.entity.Doctor;
import com.kundan.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping
	public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor){
		doctorService.addDoctor(doctor);
		return ResponseEntity.ok("Sucessfully doctor added !!!");
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctor(){
		List<Doctor> doctor =  doctorService.getAllDoctors();
		return ResponseEntity.ok().body(doctor);	
	}
	
	@PreAuthorize("hasRole('DOCTOR')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getDoctorByID(@PathVariable int id){
		Optional<Doctor> doctor = doctorService.getDoctorByID(id);
		
		if(doctor.isPresent()) {
			return ResponseEntity.ok(doctor.get());
		}else {
			return ResponseEntity.status(404).body("Doctor not found...");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable int id){
		doctorService.deleteDoctor(id);
		return ResponseEntity.ok("Doctor ID deleted Successfully !!!");
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<String> updatedDoctorByID(@PathVariable int id, @RequestBody Doctor doctor){
		doctorService.updateDoctorByID(id, doctor);
		return ResponseEntity.ok("Doctor updated Successfully !!!");
	}
	
	
	@PreAuthorize("hasRole('DOCTOR')")
	@PutMapping("/{id}/status")
	public ResponseEntity<String> updateDoctorStatus(@PathVariable int id, @RequestBody Doctor doctor){
		doctorService.updateDoctorStatus(id, doctor.getStatus());
		return ResponseEntity.ok("Doctor Status updated successfully !!!");
	}
	
	
	@GetMapping("/specialization")
	public ResponseEntity<List<Doctor>> getDoctorBySpecialization(@RequestParam String specialization){
		List<Doctor> doctor =  doctorService.getDoctorBySpecialization(specialization);
		return ResponseEntity.ok().body(doctor);
	}
	
	
	@PreAuthorize("hasRole('DOCTOR')")
	@GetMapping("/myProfile")
	public ResponseEntity<?> getMyProfile(Authentication authentication){
		
		String email = authentication.getName();
		Doctor doctor = doctorService.getMyProfile(email);
		
		if(doctor != null) {
			return ResponseEntity.ok(doctor);
		}else {
			return ResponseEntity.status(404).body("Doctor not found");
		}
	}
	
	@PreAuthorize("hasRole('DOCTOR')")
	@PutMapping("/updateMyProfile")
	public ResponseEntity<String> updateMyProfile(Authentication authentication,@RequestBody Doctor doctor){
		
		String email = authentication.getName();
		
		Doctor updatingDoctor = doctorService.updateMyProfile(email, doctor);
		
		if(updatingDoctor != null) {
			return ResponseEntity.ok("Doctor Updated Successfully !!!");
		}else {
			return ResponseEntity.ok("Doctor Not Found !!!");
		}
	}

}
