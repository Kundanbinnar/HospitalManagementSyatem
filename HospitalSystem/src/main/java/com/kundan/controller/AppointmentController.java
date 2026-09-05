package com.kundan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kundan.dto.AppointmentRequest;
import com.kundan.entity.Appointment;
import com.kundan.service.AppointmentService;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping
	public ResponseEntity<String> bookAppointment(@RequestBody AppointmentRequest request, Authentication authentication){
		
		String email = authentication.getName();
				
	   try {
		      appointmentService.bookAppointment(request, email);
		      return ResponseEntity.ok("Appointment booked successfully !!!");

	   } catch (IllegalArgumentException e) {
		      return ResponseEntity.badRequest().body(e.getMessage());
	 }
	   
	}
	
	@PreAuthorize("hasRole('DOCTOR')")
	@GetMapping
	public ResponseEntity<List<Appointment>> getAllAppointment(){
		List<Appointment> appointment = appointmentService.getAllAppointment();
		return ResponseEntity.ok().body(appointment);
	}
	
	@PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getAppointmentById(@PathVariable int id){
		Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
		
		if(appointment.isPresent()) {
			return ResponseEntity.ok().body(appointment.get());
		}else {
			return ResponseEntity.status(404).body("Appointment is not found");
		}
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAppointment(@PathVariable int id){
		appointmentService.deleteAppointment(id);
		return ResponseEntity.ok("Appointment is deleted !!!");
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@PutMapping("/{id}/status")
	public ResponseEntity<String> updateAppointmentStatus(@PathVariable int id, @RequestParam String status){
		appointmentService.updateAppointmentStatus(id, status);
		return ResponseEntity.ok("Status updated successfully !!!");
		
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@GetMapping("/patientId")
	public ResponseEntity<List<Appointment>> getAppointmentByPatientId(@RequestParam int patientId){
		List<Appointment> appointment =  appointmentService.getAppointmentByPatientId(patientId);
	    return  ResponseEntity.ok().body(appointment);
	}
	
	@PreAuthorize("hasRole('DOCTOR')")
	@GetMapping("/doctorId")
	public ResponseEntity<List<Appointment>> getAppointmentByDoctorId(Authentication authentication){
		
		String email = authentication.getName();
		
		List<Appointment> appointment =  appointmentService.getAppointmentByDoctorId(email);
	    return  ResponseEntity.ok().body(appointment);
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@GetMapping("/myAppointment")
	public ResponseEntity<List<Appointment>> getAppointmentsByEmail(Authentication authentication){
		 String email = authentication.getName();
		List<Appointment> appointment = appointmentService.getMyAppointmentByEmail(email);
		return ResponseEntity.ok().body(appointment);
	}
}
