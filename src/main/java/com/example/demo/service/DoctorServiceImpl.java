package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.DoctorScheduleDto;
import com.example.demo.dto.PatientDto;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.DoctorSchedule;
import com.example.demo.entity.Patient;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.DoctorScheduleRepository;
import com.example.demo.repository.PatientRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
    
	
	private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorScheduleRepository doctorScheduleRepository;
    private final PatientRepository patientRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, DoctorScheduleRepository doctorScheduleRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorScheduleRepository= doctorScheduleRepository;
        this.patientRepository = patientRepository;
    }
    
    @Override
    public void registerDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setContactDetails(doctorDto.getContactDetails());
        doctor.setSpecialization(doctorDto.getSpecialization());

        doctorRepository.save(doctor);
    }
    
    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DoctorDto convertToDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setDoctorId(doctor.getDoctorId());
        doctorDto.setFirstName(doctor.getFirstName());
        doctorDto.setLastName(doctor.getLastName());
        doctorDto.setContactDetails(doctor.getContactDetails());
        doctorDto.setSpecialization(doctor.getSpecialization());
        // Set other properties as needed
        return doctorDto;
    }
    
    @Override
    public List<DoctorDto> getDoctorById(Long doctorId) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.setDoctorId(doctor.getDoctorId());
            doctorDto.setFirstName(doctor.getFirstName());
            doctorDto.setLastName(doctor.getLastName());
            doctorDto.setContactDetails(doctor.getContactDetails());
            doctorDto.setSpecialization(doctor.getSpecialization());
            return Collections.singletonList(doctorDto);
        } else {
            throw new NotFoundException("Doctor not found");
        }
    }
    
    @Override
    public void updateDoctor(Long doctorId, DoctorDto doctorDto) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        // Update the doctor's properties based on the provided doctorDto
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setContactDetails(doctorDto.getContactDetails());
        doctor.setSpecialization(doctorDto.getSpecialization());

        doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        doctorRepository.delete(doctor);
    }

    @Override
    public List<DoctorScheduleDto> getAllSchedules() {
        List<DoctorSchedule> schedules = doctorScheduleRepository.findAll();
        // Convert schedules to DoctorScheduleDto if necessary

        // Assuming you have a method to convert DoctorSchedule to DoctorScheduleDto
        List<DoctorScheduleDto> scheduleDtos = convertToDto(schedules);

        return scheduleDtos;
    }

	@Override
	public void addSchedule(Long doctorId, DoctorScheduleDto doctorScheduleDto) {
	    DoctorSchedule doctorSchedule = new DoctorSchedule();
	    
	    // Fetch the doctor by ID from the doctorRepository
	    Doctor doctor = doctorRepository.findById(doctorId)
	            .orElseThrow(() -> new NotFoundException("Doctor not found"));
	
	    doctorSchedule.setDoctor(doctor);
	    doctorSchedule.setDayOfWeek(doctorScheduleDto.getDayOfWeek());
	    doctorSchedule.setTimeSlot(doctorScheduleDto.getTimeSlot());
	    doctorSchedule.setAvailability(doctorScheduleDto.getAvailability());
	
	    doctorScheduleRepository.save(doctorSchedule);
	}

	
	@Override
	public void updateSchedule(Long doctorId, Long scheduleId, DoctorScheduleDto doctorScheduleDto) {
	    // Fetch the doctor by ID from the doctorRepository
	    Doctor doctor = doctorRepository.findById(doctorId)
	            .orElseThrow(() -> new NotFoundException("Doctor not found"));

	    // Fetch the doctor's schedule by ID from the doctorScheduleRepository
	    DoctorSchedule doctorSchedule = doctorScheduleRepository.findById(scheduleId)
	            .orElseThrow(() -> new NotFoundException("Schedule not found"));

	    // Update the schedule with the new data
	    doctorSchedule.setDoctor(doctor);
	    doctorSchedule.setDayOfWeek(doctorScheduleDto.getDayOfWeek());
	    doctorSchedule.setTimeSlot(doctorScheduleDto.getTimeSlot());
	    doctorSchedule.setAvailability(doctorScheduleDto.getAvailability());

	    doctorScheduleRepository.save(doctorSchedule);
	}

    
    @Override
    public List<PatientDto> getPatientDetails(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        List<PatientDto> patientDtos = new ArrayList<>();

        List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);
        for (Appointment appointment : appointments) {
            Patient patient = appointment.getPatient();
            if (patient != null) {
                PatientDto patientDto = new PatientDto();
                patientDto.setPatientId(patient.getPatientId());
                patientDto.setFirstName(patient.getFirstName());
                patientDto.setLastName(patient.getLastName());
                patientDto.setContactDetails(patient.getContactDetails());
                patientDto.setMedicalHistory(patient.getMedicalHistory());
                patientDto.setInsuranceDetails(patient.getInsuranceDetails());
                patientDtos.add(patientDto);
            }
        }

        return patientDtos;
    }
    
    private List<DoctorScheduleDto> convertToDto(List<DoctorSchedule> schedules) {
        List<DoctorScheduleDto> scheduleDtos = new ArrayList<>();
        for (DoctorSchedule schedule : schedules) {
            DoctorScheduleDto scheduleDto = new DoctorScheduleDto();
            scheduleDto.setDoctorId(schedule.getDoctor().getDoctorId());
            scheduleDto.setScheduleId(schedule.getScheduleId());
            scheduleDto.setDayOfWeek(schedule.getDayOfWeek());
            scheduleDto.setTimeSlot(schedule.getTimeSlot());
            scheduleDto.setAvailability(schedule.getAvailability());
            scheduleDtos.add(scheduleDto);
        }
        return scheduleDtos;
    }

}

