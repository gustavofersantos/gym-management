package com.managementgroup.gymmanagement.entities;

import java.util.Objects;

import com.managementgroup.gymmanagement.entities.enums.PaymentMethodType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaymentMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethodType paymentMethodType;
	
	private String details;
	
	public PaymentMethod () {
	}

	public PaymentMethod(long id, PaymentMethodType paymentMethodType, String details) {
		super();
		this.id = id;
		this.paymentMethodType = paymentMethodType;
		this.details = details;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PaymentMethodType getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(PaymentMethodType paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public int hashCode() {
		return Objects.hash(details, id, paymentMethodType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentMethod other = (PaymentMethod) obj;
		return Objects.equals(details, other.details) && id == other.id && paymentMethodType == other.paymentMethodType;
	}
	
	
}
