package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Queue;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {
    //Optional<Queue> findByAppointmentId(Long appointmentId);
	 Optional<Queue> findByAppointment(Appointment appointment);
}

