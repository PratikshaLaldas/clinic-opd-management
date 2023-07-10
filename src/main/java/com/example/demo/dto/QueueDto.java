package com.example.demo.dto;


import com.example.demo.entity.Appointment;

public class QueueDto {

    private Long queueId;
    private Long appointmentId;
    private String queueNumber;
    private String queueStatus;
	
    public QueueDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QueueDto(Long queueId, Long appointmentId, String queueNumber, String queueStatus) {
		super();
		this.queueId = queueId;
		this.appointmentId = appointmentId;
		this.queueNumber = queueNumber;
		this.queueStatus = queueStatus;
	}

	public Long getQueueId() {
		return queueId;
	}

	public void setQueueId(Long queueId) {
		this.queueId = queueId;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getQueueNumber() {
		return queueNumber;
	}

	public void setQueueNumber(String queueNumber) {
		this.queueNumber = queueNumber;
	}

	public String getQueueStatus() {
		return queueStatus;
	}

	public void setQueueStatus(String queueStatus) {
		this.queueStatus = queueStatus;
	}

	@Override
	public String toString() {
		return "QueueDto [queueId=" + queueId + ", appointmentId=" + appointmentId + ", queueNumber=" + queueNumber
				+ ", queueStatus=" + queueStatus + "]";
	}
	
   
        
}
