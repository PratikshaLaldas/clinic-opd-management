package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id")
    private Long reminderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Column(name = "reminder_type")
    private String reminderType;

    @Column(name = "reminder_date")
    private LocalDate reminderDate;

	public Reminder(Long reminderId, Appointment appointment, String reminderType, LocalDate reminderDate) {
		super();
		this.reminderId = reminderId;
		this.appointment = appointment;
		this.reminderType = reminderType;
		this.reminderDate = reminderDate;
	}

	public Reminder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getReminderId() {
		return reminderId;
	}

	public void setReminderId(Long reminderId) {
		this.reminderId = reminderId;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
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
		return "Reminder [reminderId=" + reminderId + ", appointment=" + appointment + ", reminderType=" + reminderType
				+ ", reminderDate=" + reminderDate + "]";
	}

    
}

