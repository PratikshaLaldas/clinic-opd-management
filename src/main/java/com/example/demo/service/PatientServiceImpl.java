package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.PatientDto;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository; 

    public PatientServiceImpl(PatientRepository patientRepository, AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository; 
    }

    @Override
    public void registerPatient(PatientDto patientDto) {
        // Convert PatientDto to Patient entity
        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setContactDetails(patientDto.getContactDetails());
        patient.setMedicalHistory(patientDto.getMedicalHistory());
        patient.setInsuranceDetails(patientDto.getInsuranceDetails());

        // Save the patient entity
        patientRepository.save(patient);
    }
    
    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        // Convert patients to PatientDto
        List<PatientDto> patientDtos = patients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return patientDtos;
    }
    
    private PatientDto convertToDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setPatientId(patient.getPatientId());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setContactDetails(patient.getContactDetails());
        patientDto.setMedicalHistory(patient.getMedicalHistory());
        patientDto.setInsuranceDetails(patient.getInsuranceDetails());

        return patientDto;
    }
    
    @Override
    public void updatePatient(Long patientId, PatientDto patientDto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found"));

        // Update patient details based on the received DTO
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setContactDetails(patientDto.getContactDetails());
        patient.setMedicalHistory(patientDto.getMedicalHistory());
        patient.setInsuranceDetails(patientDto.getInsuranceDetails());

        // Save the updated patient entity
        patientRepository.save(patient);
    }

    

    @Override
    public void addAppointment(AppointmentDto appointmentDto) {
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));

        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        // Create a new appointment
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setStatus("Confirmed");

        // Save the appointment entity
        appointmentRepository.save(appointment);
    }


    
    @Override
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        appointment.setStatus("Cancelled");
        //appointment.setStatus(AppointmentStatus.CANCELLED);

        // Save the updated appointment entity
        appointmentRepository.save(appointment);
    }
    
    @Override
    public void rescheduleAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        appointment.setStatus("Rescheduled");

        // Save the updated appointment entity
        appointmentRepository.save(appointment);
    }
    

    
    @Override
    public AppointmentDto findAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElse(null);

        if (appointment != null) {
            // Convert Appointment entity to AppointmentDto
            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.setAppointmentId(appointment.getAppointmentId());
            appointmentDto.setPatientId(appointment.getPatient().getPatientId());
            appointmentDto.setDoctorId(appointment.getDoctor().getDoctorId());
            appointmentDto.setAppointmentDate(appointment.getAppointmentDate());
            appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
            appointmentDto.setStatus(appointment.getStatus());

            return appointmentDto;
        } else {
            return null;
        }
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        // Delete the appointment
        appointmentRepository.delete(appointment);
    }

	
}
