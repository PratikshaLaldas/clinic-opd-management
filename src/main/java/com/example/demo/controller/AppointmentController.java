package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.PatientDto;
import com.example.demo.service.AppointmentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

   
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDto>> viewDoctorAppointments(@PathVariable Long doctorId) {
        List<AppointmentDto> appointments = appointmentService.viewDoctorAppointments(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDto>> viewPatientAppointments(@PathVariable Long patientId) {
        List<AppointmentDto> appointments = appointmentService.viewPatientAppointments(patientId);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/updateStatus/{appointmentId}")
    public ResponseEntity<String> updateAppointmentStatus(@PathVariable Long appointmentId,
                                                          @RequestParam("status") String status) {
        appointmentService.updateAppointmentStatus(appointmentId, status);
        return ResponseEntity.ok("Appointment status updated successfully");
    }

    @GetMapping("/doctor/{doctorId}/patients")
    public ResponseEntity<List<PatientDto>> viewPatientDetailsByDoctor(@PathVariable Long doctorId) {
        List<PatientDto> patients = appointmentService.viewPatientDetailsByDoctor(doctorId);
        return ResponseEntity.ok(patients);
    }
}

