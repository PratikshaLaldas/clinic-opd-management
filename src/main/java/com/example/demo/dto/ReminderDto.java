package com.example.demo.dto;

import java.time.LocalDate;

public class ReminderDto {

	 private Long reminderId;
	 private Long appointmentId;
	 private String reminderType;
	 private LocalDate reminderDate;
	
	 public ReminderDto(Long reminderId, Long appointmentId, String reminderType, LocalDate reminderDate) {
		super();
		this.reminderId = reminderId;
		this.appointmentId = appointmentId;
		this.reminderType = reminderType;
		this.reminderDate = reminderDate;
	}

	public ReminderDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getReminderId() {
		return reminderId;
	}

	public void setReminderId(Long reminderId) {
		this.reminderId = reminderId;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getReminderType() {
		return reminderType;
	}

	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

	public LocalDate getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(LocalDate reminderDate) {
		this.reminderDate = reminderDate;
	}

	@Override
	public String toString() {
		return "ReminderDto [reminderId=" + reminderId + ", appointmentId=" + appointmentId + ", reminderType="
				+ reminderType + ", reminderDate=" + reminderDate + "]";
	}
	 
	 
	
	 
	 
	 
}
