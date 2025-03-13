package com.managementgroup.gymmanagement.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managementgroup.gymmanagement.entities.Payment;
import com.managementgroup.gymmanagement.entities.Subscription;
import com.managementgroup.gymmanagement.entities.enums.PaymentMethod;
import com.managementgroup.gymmanagement.entities.enums.PaymentStatus;
import com.managementgroup.gymmanagement.repositories.PaymentRepository;
import com.managementgroup.gymmanagement.services.exceptions.ObjectNotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment createPayment(Subscription subscription, PaymentMethod paymentMethod) {
		Payment payment = new Payment();
		payment.setSubscription(subscription);
		payment.setAmount(subscription.getPlan().getPrice());
		payment.setPaymentDate(LocalDate.now());
		payment.setPaymentMethod(paymentMethod);
		payment.setPaymentStatus(PaymentStatus.PENDING);
		
		return paymentRepository.save(payment);
	}
	
	public void updatePaymentStatus(Long paymentId, PaymentStatus paymentStatus) {
		Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new ObjectNotFoundException("Pagamento não encontrado"));
		payment.setPaymentStatus(paymentStatus);
		paymentRepository.save(payment);
	}
	
}
