package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.QueueDto;
import com.example.demo.dto.StaffDto;
import com.example.demo.exception.NotFoundException;

public interface StaffService {
	void registerStaff(StaffDto staffDto);
    void deleteStaff(Long staffId);
    List<QueueDto> getAllQueueDetails();
    void addToQueue(QueueDto queueDto) throws NotFoundException;
    void removeFromQueue(Long appointmentId) throws NotFoundException;
    void updateQueueStatus(Long appointmentId) throws NotFoundException;
	
}

