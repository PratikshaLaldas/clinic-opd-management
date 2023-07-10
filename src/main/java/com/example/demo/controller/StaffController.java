package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QueueDto;
import com.example.demo.dto.ReminderDto;
import com.example.demo.dto.StaffDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.ReminderService;
import com.example.demo.service.StaffService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;
    private final ReminderService reminderService;
   
    public StaffController(StaffService staffService, ReminderService reminderService) {
        this.staffService = staffService;
        this.reminderService = reminderService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerStaff(@RequestBody StaffDto staffDto) {
        //Staff staff = new Staff(staffDto.getFirstName(), staffDto.getLastName());
        staffService.registerStaff(staffDto);
        return ResponseEntity.ok("Staff registered successfully");
    }

    @DeleteMapping("/{staffId}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long staffId) {
        staffService.deleteStaff(staffId);
        return ResponseEntity.ok("Staff deleted successfully");
    }
    
    @GetMapping("/queue/all")
    public ResponseEntity<List<QueueDto>> getAllQueueDetails() {
        try {
            List<QueueDto> queueDetails = staffService.getAllQueueDetails();
            return ResponseEntity.ok(queueDetails);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    
    @PostMapping("/add_to_queue")
    public ResponseEntity<Void> addToQueue(@RequestBody QueueDto queueDto) {
        try {
            staffService.addToQueue(queueDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    
    @PostMapping("/remove_from_queue/{appointmentId}")
    public ResponseEntity<String> removeFromQueue(@PathVariable Long appointmentId) {
        try {
            staffService.removeFromQueue(appointmentId);
            return ResponseEntity.ok("Patient removed from queue successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/queue/{appointmentId}")
    public ResponseEntity<String> updateQueueStatus(@PathVariable Long appointmentId) {
        try {
            staffService.updateQueueStatus(appointmentId);
            return ResponseEntity.ok("Queue status updated successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("reminder/all")
    public ResponseEntity<List<ReminderDto>> getAllReminders() {
        List<ReminderDto> reminders = reminderService.getAllReminders();
        return ResponseEntity.ok(reminders);
    }
    
    @PostMapping("/add_reminder")
    public ResponseEntity<ReminderDto> addReminder(@RequestBody ReminderDto reminderDto) {
        ReminderDto createdReminder = reminderService.addReminder(reminderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReminder);
    }

    @PutMapping("/update_reminder/{reminderId}")
    public ResponseEntity<ReminderDto> updateReminder(@PathVariable("reminderId") Long reminderId,
                                                      @RequestBody ReminderDto reminderDto) {
        ReminderDto updatedReminder = reminderService.updateReminder(reminderId, reminderDto);
        return ResponseEntity.ok(updatedReminder);
    }

    @DeleteMapping("/delete_reminder/{reminderId}")
    public ResponseEntity<Void> deleteReminder(@PathVariable("reminderId") Long reminderId) {
        reminderService.deleteReminder(reminderId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/send_reminder/{reminderId}/send")
    public ResponseEntity<Void> sendReminderMessage(@PathVariable("reminderId") Long reminderId) {
        reminderService.sendReminderMessage(reminderId);
        return ResponseEntity.ok().build();
    }
    
}
