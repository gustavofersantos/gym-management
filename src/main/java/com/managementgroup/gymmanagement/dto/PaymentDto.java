package com.managementgroup.gymmanagement.dto;

import java.time.LocalDateTime;

import com.managementgroup.gymmanagement.entities.Payment;
import com.managementgroup.gymmanagement.entities.PaymentMethod;
import com.managementgroup.gymmanagement.entities.enums.PaymentStatus;

public class PaymentDto {
	
	private Long id;
	private Double amount;
	private LocalDateTime paymentDate;
	private PaymentStatus paymentStatus;
	private PaymentMethod paymentMethod;

	public PaymentDto() {

	}

	public PaymentDto(Long id, Double amount, LocalDateTime paymentDate, PaymentStatus paymentStatus,
			PaymentMethod paymentMethod) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
	}
	
	public PaymentDto(Payment payment) {
	    this.id = payment.getId();
	    this.amount = payment.getAmount();
	    this.paymentDate = payment.getPaymentDate();
	    this.paymentStatus = payment.getPaymentStatus();
	    this.paymentMethod = payment.getPaymentMethod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public static PaymentDto fromPaymentDto(Payment payment) {
		return new PaymentDto(payment.getId(), payment.getAmount(), payment.getPaymentDate(),
				payment.getPaymentStatus(), payment.getPaymentMethod());
	}

}
