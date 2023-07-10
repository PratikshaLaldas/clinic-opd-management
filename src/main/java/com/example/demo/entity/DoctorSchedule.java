package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class DoctorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "time_slot")
    private String timeSlot;

    @Column(name = "availability")
    private int availability;

	public DoctorSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorSchedule(Long scheduleId, Doctor doctor, String dayOfWeek, String timeSlot, int availability) {
		super();
		this.scheduleId = scheduleId;
		this.doctor = doctor;
		this.dayOfWeek = dayOfWeek;
		this.timeSlot = timeSlot;
		this.availability = availability;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "DoctorSchedule [scheduleId=" + scheduleId + ", doctor=" + doctor + ", dayOfWeek=" + dayOfWeek
				+ ", timeSlot=" + timeSlot + ", availability=" + availability + "]";
	}



	
    
}


