package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.DoctorScheduleDto;
import com.example.demo.dto.PatientDto;

public interface DoctorService {
    // Existing methods
	void registerDoctor(DoctorDto doctorDto);
	List<DoctorDto> getAllDoctors();
	List<DoctorDto> getDoctorById(Long doctorId);
	void updateDoctor(Long doctorId, DoctorDto doctorDto);
    void deleteDoctor(Long doctorId);
    List<DoctorScheduleDto> getAllSchedules();
    void addSchedule(Long doctorId, DoctorScheduleDto doctorScheduleDto);
    void updateSchedule(Long doctorId, Long scheduleId, DoctorScheduleDto doctorScheduleDto);
    List<PatientDto> getPatientDetails(Long appointmentId);
	
}

