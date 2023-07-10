package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.DoctorScheduleDto;
import com.example.demo.dto.PatientDto;
import com.example.demo.service.DoctorService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(@RequestBody DoctorDto doctorDto) {
        doctorService.registerDoctor(doctorDto);
        return ResponseEntity.ok("Doctor registered successfully");
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        List<DoctorDto> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }
    
    @GetMapping("/{doctorId}")
    public ResponseEntity<List<DoctorDto>> getDoctorById(@PathVariable Long doctorId) {
        List<DoctorDto> doctors = doctorService.getDoctorById(doctorId);
        return ResponseEntity.ok(doctors);
    }
    
    @PutMapping("update/{doctorId}")
    public ResponseEntity<String> updateDoctor(@PathVariable Long doctorId, @RequestBody DoctorDto doctorDto) {
        doctorService.updateDoctor(doctorId, doctorDto);
        return ResponseEntity.ok("Doctor details updated successfully");
    }

    
    @DeleteMapping("delete/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    @GetMapping("/allschedules")
    public ResponseEntity<List<DoctorScheduleDto>> getAllSchedules() {
        List<DoctorScheduleDto> schedules = doctorService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }
    
    @PostMapping("/{doctorId}/schedules")
    public ResponseEntity<String> addSchedule(@PathVariable Long doctorId, @RequestBody DoctorScheduleDto doctorScheduleDto) {
        doctorService.addSchedule(doctorId, doctorScheduleDto);
        return ResponseEntity.ok("Schedule added successfully");
    }

    
    @PutMapping("/{doctorId}/update_schedule/{scheduleId}")
    public ResponseEntity<String> updateSchedule(@PathVariable Long doctorId, @PathVariable Long scheduleId, @RequestBody DoctorScheduleDto doctorScheduleDto) {
        doctorService.updateSchedule(doctorId, scheduleId, doctorScheduleDto);
        return ResponseEntity.ok("Schedule updated successfully");
    }


    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<PatientDto>> getPatientDetails(@PathVariable Long doctorId) {
        List<PatientDto> patientDetails = doctorService.getPatientDetails(doctorId);
        if (patientDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patientDetails);
    }


}

