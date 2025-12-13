package com.kundan.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kundan.entity.Prescription;
import com.kundan.service.PrescriptionService;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

	@Autowired
	private PrescriptionService prescriptionService;
	
	@PostMapping
	public ResponseEntity<String> addPrescription(@RequestBody Prescription prescription){
		prescriptionService.addPrescription(prescription);
		return ResponseEntity.ok("Prescription Added successfully !!!");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPrescriptionById(@PathVariable int id){
		Optional<Prescription> prescription =  prescriptionService.getPrescriptionById(id);
		
		if(prescription.isPresent()) {
			return ResponseEntity.ok().body(prescription.get());
		}else {
			return ResponseEntity.status(404).body("Prescritpion ID not found");
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePrescription(@PathVariable int id ){
		prescriptionService.deletePrescription(id);
		return ResponseEntity.ok().body("Prescription deleted Successfully");
	}
	
	
	@PutMapping("/{id}/description")
	public ResponseEntity<String> updateDescription(@PathVariable int id, @RequestBody Map<String, String> body){
		String description = body.get("description");	
		prescriptionService.updateDescription(id, description);
			return ResponseEntity.ok("Description updated successfully !!!");
	}
}
