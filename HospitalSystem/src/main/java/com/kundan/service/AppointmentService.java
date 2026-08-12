package com.kundan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kundan.entity.Appointment;
import com.kundan.entity.Patient;
import com.kundan.repository.AppointmentRepository;
import com.kundan.repository.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private PatientRepository patientRepository;
	
	public Appointment bookAppointment(Appointment appointment) {
		return appointmentRepo.save(appointment);
	}
	
	public List<Appointment> getAllAppointment(){
		return appointmentRepo.findAll();
	}
	
	public Optional<Appointment> getAppointmentById(int id) {
		return appointmentRepo.findById(id);
	}
	
	public Appointment updateAppointmentStatus(int id, String status) {
		Optional<Appointment> appointment = appointmentRepo.findById(id);
		
		if(appointment.isPresent()) {
			Appointment existAppointment = appointment.get();
			existAppointment.setStatus(status);
			
			return appointmentRepo.save(existAppointment);
		}else {
			 throw new IllegalArgumentException("ID not found ");
		}
	}
	
	public void deleteAppointment(int id) {
		appointmentRepo.deleteById(id);
	}
	
	public List<Appointment> getAppointmentByPatientId(int patientId){
		return appointmentRepo.findByPatientId(patientId);
	}
	
	public List<Appointment> getAppointmentByDoctorId(int doctorId){
		return appointmentRepo.findByDoctorId(doctorId);
	}
	
	public List<Appointment> getMyAppointmentByEmail(String email){
		Patient patient = patientRepository.findByEmailId(email);
		
		 if (patient == null) {
		        throw new RuntimeException("Patient not found");
		    }
		
		 return appointmentRepo.findByPatientId(patient.getId());
	}
	
}
