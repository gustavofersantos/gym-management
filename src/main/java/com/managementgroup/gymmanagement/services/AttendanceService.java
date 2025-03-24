package com.managementgroup.gymmanagement.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managementgroup.gymmanagement.dto.AttendanceDto;
import com.managementgroup.gymmanagement.entities.Attendance;
import com.managementgroup.gymmanagement.entities.User;
import com.managementgroup.gymmanagement.repositories.AttendanceRepository;
import com.managementgroup.gymmanagement.repositories.UserRepository;
import com.managementgroup.gymmanagement.services.exceptions.ObjectNotFoundException;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public AttendanceDto registerAttendance(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		Attendance attendance = new Attendance();
		attendance.setUser(user);
		attendance.setMoment(LocalDateTime.now());
		
		attendanceRepository.save(attendance);
		
		return new AttendanceDto(userId, attendance.getMoment());
	}
	
	public List<Attendance> getAttendanceByUser(Long userId) {
        return attendanceRepository.findByUserId(userId);
    }
}
