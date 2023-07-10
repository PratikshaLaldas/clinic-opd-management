package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "Queue")
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queue_id")
    private Long queueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Column(name = "queue_number")
    private String queueNumber;

    @Column(name = "queue_status")
    private String queueStatus;

	public Queue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Queue(Long queueId, Appointment appointment, String queueNumber, String queueStatus) {
		super();
		this.queueId = queueId;
		this.appointment = appointment;
		this.queueNumber = queueNumber;
		this.queueStatus = queueStatus;
	}

	public Long getQueueId() {
		return queueId;
	}

	public void setQueueId(Long queueId) {
		this.queueId = queueId;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
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
		return "Queue [queueId=" + queueId + ", appointment=" + appointment + ", queueNumber=" + queueNumber
				+ ", queueStatus=" + queueStatus + "]";
	}

    
    
    
}

