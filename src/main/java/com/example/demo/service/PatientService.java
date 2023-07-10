package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.PatientDto;

public interface PatientService {
    void registerPatient(PatientDto patientDto);
    List<PatientDto> getAllPatients();
    void updatePatient(Long patientId, PatientDto patientDto);
    void addAppointment(AppointmentDto appointmentDto);
    void cancelAppointment(Long appointmentId);
	void rescheduleAppointment(Long appointmentId);
    AppointmentDto findAppointment(Long appointmentId);
    void deleteAppointment(Long appointmentId);
	
}



