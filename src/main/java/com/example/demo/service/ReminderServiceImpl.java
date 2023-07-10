package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ReminderDto;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Reminder;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.ReminderRepository;

@Service
public class ReminderServiceImpl implements ReminderService {
    private final ReminderRepository reminderRepository;
    private final AppointmentRepository appointmentRepository;
    private final EmailService emailService;

    public ReminderServiceImpl(ReminderRepository reminderRepository,
                               AppointmentRepository appointmentRepository,
                               EmailService emailService) {
        this.reminderRepository = reminderRepository;
        this.appointmentRepository = appointmentRepository;
        this.emailService = emailService;
    }
    
    

    @Override
    public List<ReminderDto> getAllReminders() {
        List<Reminder> reminders = reminderRepository.findAll();
        return reminders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public ReminderDto addReminder(ReminderDto reminderDto) {
        Appointment appointment = appointmentRepository.findById(reminderDto.getAppointmentId())
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        LocalDate appointmentDate = appointment.getAppointmentDate();
        LocalDate reminderDate = appointmentDate.minusDays(1); // Set reminder date as one day before the appointment date

        Reminder reminder = new Reminder();
        reminder.setAppointment(appointment);
        reminder.setReminderType(reminderDto.getReminderType());
        reminder.setReminderDate(reminderDate);

        Reminder savedReminder = reminderRepository.save(reminder);
        return convertToDto(savedReminder);
    }
    
    
    @Override
    public ReminderDto updateReminder(Long reminderId, ReminderDto reminderDto) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new NotFoundException("Reminder not found"));

        // Perform necessary validation and logic to ensure the reminder date is set before the appointment date
        LocalDate appointmentDate = reminder.getAppointment().getAppointmentDate();
        LocalDate reminderDate = reminderDto.getReminderDate();
        if (reminderDate.isAfter(appointmentDate)) {
            throw new InvalidInputException("Reminder date cannot be after the appointment date");
        }

        reminder.setReminderType(reminderDto.getReminderType());
        reminder.setReminderDate(reminderDto.getReminderDate());

        Reminder updatedReminder = reminderRepository.save(reminder);
        return convertToDto(updatedReminder);
    }

    @Override
    public void deleteReminder(Long reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new NotFoundException("Reminder not found"));

        reminderRepository.delete(reminder);
    }

    @Override
    public void sendReminderMessage(Long reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new NotFoundException("Reminder not found"));

        // Perform necessary logic to send the reminder message to the patient's email
        String patientEmail = reminder.getAppointment().getPatient().getContactDetails();
        String reminderMessage = generateReminderMessage(reminder);

        // Use the emailService to send the reminder message
        emailService.sendEmail(patientEmail, "Appointment Reminder", reminderMessage);
    }

    // Helper method to convert Reminder entity to ReminderDTO
    private ReminderDto convertToDto(Reminder reminder) {
        ReminderDto reminderDto = new ReminderDto();
        reminderDto.setReminderId(reminder.getReminderId());
        reminderDto.setAppointmentId(reminder.getAppointment().getAppointmentId());
        reminderDto.setReminderType(reminder.getReminderType());
        reminderDto.setReminderDate(reminder.getReminderDate());

        return reminderDto;
    }

    // Helper method to generate the reminder message based on the reminder details
    private String generateReminderMessage(Reminder reminder) {
        
    	// Get the appointment details associated with the reminder
        Appointment appointment = reminder.getAppointment();

        // Get the doctor's information
        Doctor doctor = appointment.getDoctor();
        String doctorName = doctor.getFirstName() + " " + doctor.getLastName();

        String message = "Dear patient, this is a reminder for your appointment tomorrow. "
                + "Your appointment details are as follows:\n"
                + "Date: " + appointment.getAppointmentDate() + "\n"
                + "Time: " + appointment.getAppointmentTime() + "\n"
                + "Doctor: " + doctorName + "\n"
                + "Please remember to bring any necessary documents or reports. "
                + "We are looking forward to seeing you!";

        return message;
    }


}
