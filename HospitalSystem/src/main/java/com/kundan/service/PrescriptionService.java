package com.kundan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kundan.dto.PrescriptionResponse;
import com.kundan.entity.Appointment;
import com.kundan.entity.Patient;
import com.kundan.entity.Prescription;
import com.kundan.repository.PatientRepository;
import com.kundan.repository.PrescriptionRepository;

@Service
public class PrescriptionService {
	
	@Autowired
	private PrescriptionRepository prescriptionRepo;
	
	@Autowired
	private PatientRepository patientRepo;
	
	public Prescription addPrescription(Prescription prescription) {
		return prescriptionRepo.save(prescription);
	}
	
	public Optional<Prescription> getPrescriptionById(int id) {
		return prescriptionRepo.findById(id);
	}

	public void deletePrescription(int id) {
		prescriptionRepo.deleteById(id);
	}
	
	public Prescription updateDescription(int id, String description) {
		Optional<Prescription> prescription = prescriptionRepo.findById(id);
		
		if(prescription.isPresent()) {
			Prescription existPrescription = prescription.get();
			existPrescription.setDescription(description);
			
			return prescriptionRepo.save(existPrescription);
		}else {
			throw new IllegalArgumentException("Prescription id not found");
		}
	}
	
	public List<PrescriptionResponse> getAllPrescriptionByEmail(String email){
		
		Patient patient = patientRepo.findByEmailId(email);
		
		if(patient == null ) {
			 throw new RuntimeException("Patient not found");
		}
		
		List<Prescription> prescriptions= prescriptionRepo.findByPatientId(patient.getId());
		
		List<PrescriptionResponse> response  = new ArrayList<>();
		
		for(Prescription prescription : prescriptions) {
			Appointment appointment = prescription.getAppointment();
			
			response.add(new PrescriptionResponse(
					prescription.getId(),
	                prescription.getCreateAt(),
	                prescription.getDescription(),
	                appointment.getAppointmentDate(),
	                appointment.getAppointmentTime(),
	                appointment.getAppointmentReason(),
	                appointment.getDoctor().getName()
					)); 
		}
		
		return response;
	}
}