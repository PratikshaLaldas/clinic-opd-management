package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.PatientDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.PatientService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patients")
public class PatientController {

	private final PatientService patientService;
	private final AppointmentService appointmentService;

	public PatientController(PatientService patientService, AppointmentService appointmentService) {
		this.patientService = patientService;
		this.appointmentService = appointmentService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerPatient(@RequestBody PatientDto patientDto) {
		patientService.registerPatient(patientDto);
		return ResponseEntity.ok("Patient registered successfully");
	}

	@GetMapping("/allpatients")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
	
	@PutMapping("/update_patients/{patientId}")
	public ResponseEntity<String> updatePatient(@PathVariable Long patientId, @RequestBody PatientDto patientDto) {
		patientService.updatePatient(patientId, patientDto);
		return ResponseEntity.ok("Patient details updated successfully");
	}

	@PostMapping("/add_appointment")
	public ResponseEntity<String> addAppointment(@RequestBody AppointmentDto appointmentDto) {
		patientService.addAppointment(appointmentDto);
		return ResponseEntity.ok("Appointment added successfully");
	}

	@PostMapping("/cancel_appointment")
	public ResponseEntity<String> cancelAppointment(@RequestBody AppointmentDto appointmentDto) {
		Long appointmentId = appointmentDto.getAppointmentId();
		patientService.cancelAppointment(appointmentId);
		String message = "Appointment with ID " + appointmentId + " canceled successfully";
		return ResponseEntity.ok(message);
	}

	@PostMapping("/reschedule_appointment")
	public ResponseEntity<String> rescheduleAppointment(@RequestBody AppointmentDto appointmentDto) {
		Long appointmentId = appointmentDto.getAppointmentId();
		patientService.rescheduleAppointment(appointmentId);
		String message = "Appointment with ID " + appointmentId + " rescheduled successfully";
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/view_patient_appointments")
    public ResponseEntity<List<AppointmentDto>> viewPatientAppointments() {
        List<AppointmentDto> appointments = appointmentService.viewPatientAppointments();
        return ResponseEntity.ok(appointments);
    }

	@GetMapping("/find_appointments/{appointmentId}")
	public ResponseEntity<AppointmentDto> findAppointment(@PathVariable Long appointmentId) {
		AppointmentDto appointmentDto = patientService.findAppointment(appointmentId);
		if (appointmentDto != null) {
			return ResponseEntity.ok(appointmentDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	//more suitable than upper method
	  
	  @GetMapping("/view_patient_appointments/{patientId}") public
	  ResponseEntity<List<AppointmentDto>> viewPatientAppointments(@PathVariable
	 Long patientId) { List<AppointmentDto> appointments =
	  appointmentService.viewPatientAppointments(patientId);
	 return ResponseEntity.ok(appointments); }
	 
	

	@DeleteMapping("/delete_appointments/{appointmentId}")
	public ResponseEntity<String> deleteAppointment(@PathVariable Long appointmentId) {
		patientService.deleteAppointment(appointmentId);
		return ResponseEntity.ok("Appointment deleted successfully");
	}
}
