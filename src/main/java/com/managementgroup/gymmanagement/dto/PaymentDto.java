package com.managementgroup.gymmanagement.dto;

import java.time.LocalDate;

import com.managementgroup.gymmanagement.entities.Payment;
import com.managementgroup.gymmanagement.entities.enums.PaymentStatus;

public class PaymentDto {
	
	private Long id;
	private Double amount;
	private LocalDate paymentDate;
	private PaymentStatus paymentStatus;
	private String paymentMethod;

	public PaymentDto() {

	}

	public PaymentDto(Long id, Double amount, LocalDate paymentDate, PaymentStatus paymentStatus,
			String paymentMethod) {
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
	    this.paymentMethod = payment.getPaymentMethod().name();
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

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public static PaymentDto fromPaymentDto(Payment payment) {
		return new PaymentDto(payment.getId(), payment.getAmount(), payment.getPaymentDate(),
				payment.getPaymentStatus(), payment.getPaymentMethod().name());
	}

}
