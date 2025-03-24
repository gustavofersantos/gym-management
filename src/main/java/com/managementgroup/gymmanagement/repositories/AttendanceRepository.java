package com.managementgroup.gymmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managementgroup.gymmanagement.entities.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	List<Attendance> findByUserId(Long userId);
}
