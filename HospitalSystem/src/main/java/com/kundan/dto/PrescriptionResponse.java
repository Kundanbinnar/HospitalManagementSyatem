package com.kundan.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PrescriptionResponse {
	
		private int id;
		private LocalDateTime createAt;
	    private String description;

	    private LocalDate appointmentDate;
	    private LocalTime appointmentTime;
	    private String appointmentReason;

	    private String doctorName;

		public PrescriptionResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		 public PrescriptionResponse(
		            int id,
		            LocalDateTime createAt,
		            String description,
		            LocalDate appointmentDate,
		            LocalTime appointmentTime,
		            String appointmentReason,
		            String doctorName) {

		        this.id = id;
		        this.createAt = createAt;
		        this.description = description;
		        this.appointmentDate = appointmentDate;
		        this.appointmentTime = appointmentTime;
		        this.appointmentReason = appointmentReason;
		        this.doctorName = doctorName;
		    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public LocalDateTime getCreateAt() {
			return createAt;
		}

		public void setCreateAt(LocalDateTime createAt) {
			this.createAt = createAt;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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

		public String getAppointmentReason() {
			return appointmentReason;
		}

		public void setAppointmentReason(String appointmentReason) {
			this.appointmentReason = appointmentReason;
		}

		public String getDoctorName() {
			return doctorName;
		}

		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}
	    
	    

}
