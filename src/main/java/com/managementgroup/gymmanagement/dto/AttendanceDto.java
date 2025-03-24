package com.managementgroup.gymmanagement.dto;

import java.time.LocalDateTime;

public class AttendanceDto {
	
	private Long userId;
	private LocalDateTime moment;
	
	public AttendanceDto () {
	}

	public AttendanceDto(Long userId, LocalDateTime moment) {
		super();
		this.userId = userId;
		this.moment = moment;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}
	
	
}
