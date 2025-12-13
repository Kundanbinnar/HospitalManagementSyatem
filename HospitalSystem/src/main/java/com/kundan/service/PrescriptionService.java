package com.kundan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kundan.entity.Prescription;
import com.kundan.repository.PrescriptionRepository;

@Service
public class PrescriptionService {
	
	@Autowired
	private PrescriptionRepository prescriptionRepo;
	
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
}
