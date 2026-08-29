package com.kundan.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequest {

	    private int patientId;
	    private int doctorId;

	    private LocalDate appointmentDate;
	    private LocalTime appointmentTime;

	    private String status;
	    
	    private String appointmentReason;
	    

		public AppointmentRequest() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getPatientId() {
			return patientId;
		}

		public void setPatientId(int patientId) {
			this.patientId = patientId;
		}

		public int getDoctorId() {
			return doctorId;
		}

		public void setDoctorId(int doctorId) {
			this.doctorId = doctorId;
		}

		public LocalDate getAppointmentDate() {
			return appointmentDate;
		}

		public void setAppointmentDate(LocalDate appointmentDate) {
			this.appointmentDate = appointmentDate;
		}

		public LocalTime getAppointmentTime() {
			return appointmentTime;
		}

		public void setAppointmentTime(LocalTime appointmentTime) {
			this.appointmentTime = appointmentTime;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getAppointmentReason() {
			return appointmentReason;
		}

		public void setAppointmentReason(String appointmentReason) {
			this.appointmentReason = appointmentReason;
		}

		
 

}
