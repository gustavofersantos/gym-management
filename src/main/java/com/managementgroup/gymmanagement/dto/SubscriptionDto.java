package com.managementgroup.gymmanagement.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import com.managementgroup.gymmanagement.entities.Subscription;

public class SubscriptionDto {
	
	private Long id;
    private String userCpf;
    private String planName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    public SubscriptionDto() {
    	
    }

	public SubscriptionDto(Long id, String userCpf, String planName, LocalDateTime startDate, LocalDateTime endDate) {
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
    
	public static SubscriptionDto fromSubscription(Subscription subscription) {
        Objects.requireNonNull(subscription, "Subscription não pode ser nulo");
        Objects.requireNonNull(subscription.getUser(), "Usuário não pode ser nulo");
        Objects.requireNonNull(subscription.getPlan(), "Plano não pode ser nulo");

        return new SubscriptionDto(
            subscription.getId(),
            subscription.getUser().getCpf(),
            subscription.getPlan().getName(),
            subscription.getStartDate(),
            subscription.getEndDate()
        );
    }
}
