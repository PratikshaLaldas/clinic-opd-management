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
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository,
                                  PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<AppointmentDto> viewPatientAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        // Convert appointments to AppointmentDto
        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return appointmentDtos;
    }

    private AppointmentDto convertToDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setAppointmentId(appointment.getAppointmentId());
        appointmentDto.setPatientId(appointment.getPatient().getPatientId());
        appointmentDto.setDoctorId(appointment.getDoctor().getDoctorId());
        appointmentDto.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
        appointmentDto.setStatus(appointment.getStatus());
 

        return appointmentDto;
    }
    
    @Override
    public List<AppointmentDto> viewDoctorAppointments(Long doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);
        return convertAppointmentsToDto(appointments);
    }

    @Override
    public List<AppointmentDto> viewPatientAppointments(Long patientId) {
        Patient patient = getPatientById(patientId);
        List<Appointment> appointments = appointmentRepository.findByPatient(patient);
        return convertAppointmentsToDto(appointments);
    }

    @Override
    public void updateAppointmentStatus(Long appointmentId, String status) {
        Appointment appointment = getAppointmentById(appointmentId);
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    @Override
    public List<PatientDto> viewPatientDetailsByDoctor(Long doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);
        List<Patient> patients = appointments.stream()
                .map(Appointment::getPatient)
                .collect(Collectors.toList());
        return convertPatientsToDto(patients);
    }

    private Appointment getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));
    }

    private Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));
    }

    private Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found"));
    }

    private List<AppointmentDto> convertAppointmentsToDto(List<Appointment> appointments) {
        return appointments.stream()
                .map(appointment -> {
                    AppointmentDto appointmentDto = new AppointmentDto();
                    appointmentDto.setAppointmentId(appointment.getAppointmentId());
                    appointmentDto.setPatientId(appointment.getPatient().getPatientId());
                    appointmentDto.setDoctorId(appointment.getDoctor().getDoctorId());
                    appointmentDto.setAppointmentDate(appointment.getAppointmentDate());
                    appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
                    appointmentDto.setStatus(appointment.getStatus());
                    return appointmentDto;
                })
                .collect(Collectors.toList());
    }

    private List<PatientDto> convertPatientsToDto(List<Patient> patients) {
        return patients.stream()
                .map(patient -> {
                    PatientDto patientDto = new PatientDto();
                    patientDto.setPatientId(patient.getPatientId());
                    patientDto.setFirstName(patient.getFirstName());
                    patientDto.setLastName(patient.getLastName());
                    patientDto.setContactDetails(patient.getContactDetails());
                    patientDto.setMedicalHistory(patient.getMedicalHistory());
                    patientDto.setInsuranceDetails(patient.getInsuranceDetails());
                    return patientDto;
                })
                .collect(Collectors.toList());
    }
}
