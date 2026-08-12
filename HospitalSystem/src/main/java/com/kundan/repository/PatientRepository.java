package com.kundan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kundan.entity.Appointment;
import com.kundan.entity.Patient;
import com.kundan.entity.User;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	//public Patient searchByEmail(String email);
	
	public Patient findByUser_Id(int id);
	public Patient findByEmailId(String emailId);
}
