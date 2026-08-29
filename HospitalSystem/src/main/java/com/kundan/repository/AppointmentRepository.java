package com.kundan.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kundan.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	public List<Appointment> findByPatientId(int patientId);
	
	public List<Appointment> findByDoctorId(int doctorId);
	
	//public boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(int doctorId , LocalDate appointmentDate, LocalTime appointmentTime);
	
	
	@Query("""
		    SELECT COUNT(a) > 0
		    FROM Appointment a
		    WHERE a.doctor.id = :doctorId
		    AND a.appointmentDate = :appointmentDate
		    AND a.appointmentTime >= :startTime
		    AND a.appointmentTime < :endTime
		""")
		boolean existsAppointmentInTimeRange(int doctorId, LocalDate appointmentDate, LocalTime startTime,LocalTime endTime);
	
}
