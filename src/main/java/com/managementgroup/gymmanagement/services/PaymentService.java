package com.managementgroup.gymmanagement.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managementgroup.gymmanagement.dto.PaymentDto;
import com.managementgroup.gymmanagement.entities.Payment;
import com.managementgroup.gymmanagement.entities.Subscription;
import com.managementgroup.gymmanagement.entities.enums.PaymentMethod;
import com.managementgroup.gymmanagement.entities.enums.PaymentStatus;
import com.managementgroup.gymmanagement.entities.enums.SubscriptionStatus;
import com.managementgroup.gymmanagement.repositories.PaymentRepository;
import com.managementgroup.gymmanagement.repositories.SubscriptionRepository;
import com.managementgroup.gymmanagement.services.exceptions.ObjectNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public PaymentDto createPayment(Long subscriptionId, PaymentMethod paymentMethod, Double amount) {
		Subscription subscription = subscriptionRepository.findById(subscriptionId)
				.orElseThrow(() -> new ObjectNotFoundException("Assinatura não encontrada"));

		if (subscription.getStatus() != SubscriptionStatus.PENDING) {
			throw new IllegalStateException("A assinatura já foi paga ou não pode ser paga");
		}

		Payment payment = new Payment();
		payment.setSubscription(subscription);
		payment.setAmount(amount);
		payment.setPaymentMethod(paymentMethod);
		payment.setPaymentStatus(PaymentStatus.PENDING);

		subscription.setStatus(SubscriptionStatus.ACTIVATED);
		subscriptionRepository.save(subscription);

		Payment savedPayment = paymentRepository.save(payment);
		return PaymentDto.fromPaymentDto(savedPayment);
	}

	public void updatePaymentStatus(Long paymentId, PaymentStatus paymentStatus) {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado"));

		payment.setPaymentStatus(paymentStatus);
		paymentRepository.save(payment);
	}
	
	public List<PaymentDto> getAllPayments() {
		return paymentRepository.findAll().stream().map(PaymentDto::new).collect(Collectors.toList());
	}
	
	public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        return new PaymentDto(payment);
    }
}
