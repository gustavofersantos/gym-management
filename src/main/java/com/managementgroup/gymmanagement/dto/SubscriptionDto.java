package com.managementgroup.gymmanagement.dto;

import java.time.LocalDate;

import com.managementgroup.gymmanagement.entities.Subscription;

public class SubscriptionDto {
	
	private Long id;
    private String userCpf;
    private String planName;
    private LocalDate startDate;
    private LocalDate endDate;
    
    public SubscriptionDto() {
    	
    }

	public SubscriptionDto(Long id, String userCpf, String planName, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.userCpf = userCpf;
		this.planName = planName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCpf() {
		return userCpf;
	}

	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
    
    public static SubscriptionDto fromSubscriptionDto(Subscription subscription) {
    	return new SubscriptionDto(subscription.getId(), subscription.getUser().getCpf(), subscription.getPlan().getName(), subscription.getStartDate(), subscription.getEndDate());
    }
	
}
