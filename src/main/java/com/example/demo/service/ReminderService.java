package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ReminderDto;

public interface ReminderService {
	
	List<ReminderDto> getAllReminders();
	
    ReminderDto addReminder(ReminderDto reminderDto);

    ReminderDto updateReminder(Long reminderId, ReminderDto reminderDto);

    void deleteReminder(Long reminderId);

    void sendReminderMessage(Long reminderId);
}

