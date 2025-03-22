package com.managementgroup.gymmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.managementgroup.gymmanagement.dto.PaymentDto;
import com.managementgroup.gymmanagement.entities.PaymentMethod;
import com.managementgroup.gymmanagement.entities.enums.PaymentStatus;
import com.managementgroup.gymmanagement.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping
	public ResponseEntity<PaymentDto> createPayment(@RequestParam Long subscriptionId,
			@RequestParam PaymentMethod paymentMethod, @RequestParam Double amount) {
		PaymentDto paymentDto = paymentService.createPayment(subscriptionId, paymentMethod, amount);
		return ResponseEntity.ok(paymentDto);
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<Void> updatePaymentStatus(@PathVariable Long id, @RequestParam PaymentStatus paymentStatus) {
		paymentService.updatePaymentStatus(id, paymentStatus);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<PaymentDto>> getAllPayments() {
		List<PaymentDto> payments = paymentService.getAllPayments();
		return ResponseEntity.ok(payments);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
		PaymentDto paymentDto = paymentService.getPaymentById(id);
		return ResponseEntity.ok(paymentDto);
	}
}