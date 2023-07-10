package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.PatientDto;

public interface AppointmentService {
	List<AppointmentDto> viewPatientAppointments();
    List<AppointmentDto> viewDoctorAppointments(Long doctorId);
    List<AppointmentDto> viewPatientAppointments(Long patientId);
    void updateAppointmentStatus(Long appointmentId, String status);
    List<PatientDto> viewPatientDetailsByDoctor(Long doctorId);
}

