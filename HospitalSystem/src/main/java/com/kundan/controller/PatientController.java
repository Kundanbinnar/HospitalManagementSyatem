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

import com.kundan.entity.Patient;
import com.kundan.repository.PatientRepository;
import com.kundan.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping
	public ResponseEntity<String> addPatient(@PathVariable int userId, @RequestBody Patient patient){
		patientService.addPatient(userId, patient);
		return ResponseEntity.ok("Patient added Successfully !!!");
	}
	
	@PreAuthorize("hasRole('DOCTOR')")
	@GetMapping
	public ResponseEntity<List<Patient>> getAllPatient(){
		List<Patient> patient =  patientService.getAllPatient();
		return ResponseEntity.ok().body(patient);
	}
	
	@PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable int id){
		Optional<Patient> patient = patientService.getPatientById(id);
		
		if(patient.isPresent()) {
			return ResponseEntity.ok(patient.get());
		}else {
			return ResponseEntity.status(404).body("Patient not found...");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable int id){
		patientService.deletePatient(id);
		return ResponseEntity.ok("Patient deleted Successfully !!!");
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updatePatientById(@PathVariable int id, @RequestBody Patient patient){
		Patient patient1 = patientService.updatePatientById(id, patient);
		
		if(patient != null) {
			return ResponseEntity.ok("Patient registered Successfully !!!");
		}else {
			return ResponseEntity.ok("Patient not found !!!");
		}
		
	}
	
	/*
	 * @GetMapping("/email")
	 *  public ResponseEntity<?> searchByEmail(@RequestParam
	 * String email){ Patient patient = patientService.searchByEmail(email);
	 * 
	 * if(patient !=null) { return ResponseEntity.ok().body(patient); }else { return
	 * ResponseEntity.status(404).body("Patient not found"); }
	 * 
	 * }
	 */
	
	@GetMapping("/{id}/exist")
	public ResponseEntity<String> existById(@PathVariable int id){
		boolean isPatient = patientService.existById(id);
		
		if(isPatient){
			return ResponseEntity.ok("Patient is Present");
		}else {
			return ResponseEntity.ok("Patient is not present");
		}
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> patientCount() {
		return ResponseEntity.ok(patientService.patientCount());
	}
	
	
	@GetMapping("/myProfile")
	public ResponseEntity<?> myProfile(Authentication authentication) {
		String email = authentication.getName();
		Patient patient = patientService.myProfile(email);
		
		if(patient != null) {
			return ResponseEntity.ok(patient);
		}
		return ResponseEntity.status(404).body("Patient not found");	
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@PutMapping("/updateMyProfile")
	public ResponseEntity<String> updateMyProfile(Authentication authentication,@RequestBody Patient patient){
		String email = authentication.getName();
		
		Patient updatingPatient = patientService.updateMyProfile(email, patient);
		if(updatingPatient != null) {
			return ResponseEntity.ok("Patient Updated Successfully !!!");
		}else {
			return ResponseEntity.ok("Patient Not Found !!!");
		}
	}

}
