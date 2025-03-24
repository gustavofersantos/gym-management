package com.managementgroup.gymmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.managementgroup.gymmanagement.dto.AttendanceDto;
import com.managementgroup.gymmanagement.entities.Attendance;
import com.managementgroup.gymmanagement.services.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	
	@PostMapping
	public ResponseEntity<AttendanceDto> registerAttendance(@RequestBody AttendanceDto attendanceDto) {
		AttendanceDto response = attendanceService.registerAttendance(attendanceDto.getUserId());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<Attendance>> getAttendanceByUser(@PathVariable Long userId) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByUser(userId);
        return ResponseEntity.ok(attendanceList);
    }
}
