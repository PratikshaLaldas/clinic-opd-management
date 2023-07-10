package com.example.demo.dto;

public class DoctorScheduleDto {
    private Long scheduleId;
    private Long doctorId;
    private String dayOfWeek;
    private String timeSlot;
    private int availability;
	
    public DoctorScheduleDto(Long scheduleId, Long doctorId, String dayOfWeek, String timeSlot, int availability) {
		super();
		this.scheduleId = scheduleId;
		this.doctorId = doctorId;
		this.dayOfWeek = dayOfWeek;
		this.timeSlot = timeSlot;
		this.availability = availability;
	}

	public DoctorScheduleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
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
		return "DoctorScheduleDto [scheduleId=" + scheduleId + ", doctorId=" + doctorId + ", dayOfWeek=" + dayOfWeek
				+ ", timeSlot=" + timeSlot + ", availability=" + availability + "]";
	}
	
    
    
    
}
