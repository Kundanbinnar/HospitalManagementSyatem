package com.kundan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kundan.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	public List<Appointment> findByPatientId(int patientId);
	
	public List<Appointment> findByDoctorId(int doctorId);
	
}
