package com.kundan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kundan.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

	List<Doctor> findBySpecialization(String specialization);
}
