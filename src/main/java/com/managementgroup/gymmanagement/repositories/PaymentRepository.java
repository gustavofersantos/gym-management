package com.managementgroup.gymmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managementgroup.gymmanagement.entities.Payment;
import com.managementgroup.gymmanagement.entities.enums.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);
}
