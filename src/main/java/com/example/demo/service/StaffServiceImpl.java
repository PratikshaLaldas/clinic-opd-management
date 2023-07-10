package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.QueueDto;
import com.example.demo.dto.StaffDto;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Queue;
import com.example.demo.entity.Staff;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.QueueRepository;
import com.example.demo.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final AppointmentRepository appointmentRepository;
    private final QueueRepository queueRepository;

    public StaffServiceImpl(StaffRepository staffRepository,AppointmentRepository appointmentRepository, QueueRepository queueRepository) {
        this.staffRepository = staffRepository;
        this.appointmentRepository = appointmentRepository;
        this.queueRepository = queueRepository;
    }

    @Override
    public void registerStaff(StaffDto staffDto) {
    	 Staff staff = new Staff();
         staff.setFirstName(staffDto.getFirstName());
         staff.setLastName(staffDto.getLastName());
         
        staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(Long staffId) {
        staffRepository.deleteById(staffId);
    }

    @Override
    public List<QueueDto> getAllQueueDetails() {
        List<Queue> queues = queueRepository.findAll();
        
        if (queues.isEmpty()) {
            throw new NotFoundException("Queue details not found");
        }
        
        return queues.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    private QueueDto convertToDto(Queue queue) {
        QueueDto queueDto = new QueueDto();
        queueDto.setQueueId(queue.getQueueId());
        queueDto.setQueueNumber(queue.getQueueNumber());
        queueDto.setAppointmentId(queue.getAppointment().getAppointmentId());
        queueDto.setQueueStatus(queue.getQueueStatus());
        
        
        return queueDto;
    }
    
    @Override
    public void addToQueue(QueueDto queueDto) throws NotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(queueDto.getAppointmentId());
    	//System.out.println(queueDto.getAppointmentId());

    	if (!appointmentOptional.isPresent()) {
            throw new NotFoundException("Appointment not found");
        }

        Appointment appointment = appointmentOptional.get();
        if (!"Confirmed".equals(appointment.getStatus())) {
            throw new RuntimeException("Cannot add to queue. Appointment status is not confirmed.");
        }

        Queue queue = new Queue();
        queue.setAppointment(appointment);
        queue.setQueueNumber(queueDto.getQueueNumber());
        queue.setQueueStatus("Waiting");
        queueRepository.save(queue);
    }
//
//    
//    @Override
//    public void removeFromQueue(Long appointmentId) throws NotFoundException {
//        Optional<Queue> queueOptional = queueRepository.findByAppointmentId(appointmentId);
//        if (queueOptional.isEmpty()) {
//            throw new NotFoundException("Queue not found");
//        }
//
//        Queue queue = queueOptional.get();
//        queueRepository.delete(queue);
//    }
//
//    @Override
//    public void updateQueueStatus(Long appointmentId) throws NotFoundException {
//        Optional<Queue> queueOptional = queueRepository.findByAppointmentId(appointmentId);
//        if (queueOptional.isEmpty()) {
//            throw new NotFoundException("Queue not found");
//        }
//
//        Queue queue = queueOptional.get();
//        Appointment appointment = queue.getAppointment();
//
//        LocalDate currentDate = LocalDate.now();
//        LocalDate appointmentDate = appointment.getAppointmentDate();
//        if (currentDate.isBefore(appointmentDate)) {
//            queue.setQueueStatus("Waiting");
//        } else if (currentDate.equals(appointmentDate)) {
//            queue.setQueueStatus("In Progress");
//        } else {
//            queue.setQueueStatus("Completed");
//        }
//
//        queueRepository.save(queue);
//    }
//    
    @Override
    public void removeFromQueue(Long appointmentId) throws NotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if (!appointmentOptional.isPresent()) {
            throw new NotFoundException("Appointment not found");
        }

        Appointment appointment = appointmentOptional.get();
        Optional<Queue> queueOptional = queueRepository.findByAppointment(appointment);
        if (!queueOptional.isPresent()) {
            throw new NotFoundException("Queue not found");
        }

        Queue queue = queueOptional.get();
        queueRepository.delete(queue);
    }

    @Override
    public void updateQueueStatus(Long appointmentId) throws NotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if (!appointmentOptional.isPresent()) {
            throw new NotFoundException("Appointment not found");
        }

        Appointment appointment = appointmentOptional.get();
        Optional<Queue> queueOptional = queueRepository.findByAppointment(appointment);
        if (!queueOptional.isPresent()) {
            throw new NotFoundException("Queue not found");
        }

        Queue queue = queueOptional.get();

        LocalDate currentDate = LocalDate.now();
        LocalDate appointmentDate = appointment.getAppointmentDate();
        if (currentDate.isBefore(appointmentDate)) {
            queue.setQueueStatus("Waiting");
        } else if (currentDate.equals(appointmentDate)) {
            queue.setQueueStatus("In Progress");
        } else {
            queue.setQueueStatus("Completed");
        }

        queueRepository.save(queue);
    }

}
