package com.kundan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kundan.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	public Patient searchByEmail(String email);
}
