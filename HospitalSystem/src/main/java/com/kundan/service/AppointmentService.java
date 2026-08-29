package com.kundan.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kundan.entity.Appointment;
import com.kundan.entity.Doctor;
import com.kundan.entity.Patient;
import com.kundan.repository.AppointmentRepository;
import com.kundan.repository.DoctorRepository;
import com.kundan.repository.PatientRepository;
import com.kundan.dto.AppointmentRequest;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public Appointment bookAppointment(AppointmentRequest request, String email) {
		
		  Patient patient = patientRepository.findByEmailId(email);

		  if (patient == null) {
		        throw new RuntimeException("Patient not found");
		    }
		  
		Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElseThrow(() -> new RuntimeException("Doctor not found"));
		
		
		LocalTime startTime = request.getAppointmentTime();
		LocalTime endTime = startTime.plusHours(1);
		
		boolean alreadyBooked = appointmentRepo.existsAppointmentInTimeRange(request.getDoctorId(),request.getAppointmentDate(), 
				startTime, endTime);
		
		if(alreadyBooked) {
		 throw new IllegalArgumentException("This slot is already booked !!!");
		}
		
		 Appointment appointment = new Appointment();
		 
		 appointment.setPatient(patient);
		 appointment.setDoctor(doctor);
		 appointment.setAppointmentDate(request.getAppointmentDate());
		 appointment.setAppointmentTime(request.getAppointmentTime());
		 appointment.setStatus(request.getStatus());
		 appointment.setAppointmentReason(request.getAppointmentReason());
		
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
	
	public List<Appointment> getAppointmentByDoctorId(String email){
		
		Doctor doctor = doctorRepository.findByUser_Email(email);
		
		if(doctor == null) {
			 throw new RuntimeException("Doctor not found");
		}
		return appointmentRepo.findByDoctorId(doctor.getId());
	}
	
	public List<Appointment> getMyAppointmentByEmail(String email){
		Patient patient = patientRepository.findByEmailId(email);
		
		 if (patient == null) {
		        throw new RuntimeException("Patient not found");
		    }
		
		 return appointmentRepo.findByPatientId(patient.getId());
	}
	
}
