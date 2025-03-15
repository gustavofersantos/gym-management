package com.managementgroup.gymmanagement.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managementgroup.gymmanagement.entities.Subscription;
import com.managementgroup.gymmanagement.entities.User;
import com.managementgroup.gymmanagement.entities.enums.SubscriptionStatus;
import com.managementgroup.gymmanagement.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	public Subscription create (Subscription subscription) {
		Optional<Subscription> alreadySubscription = subscriptionRepository.findByUserAndStatus(subscription.getUser(), subscription.getStatus());
		
		if (alreadySubscription.isPresent()) {
			throw new RuntimeException("Usuário já possui um plano ativo");
		}
		
		if (subscription.getStartDate() == null) {
			subscription.setStartDate(LocalDate.now());
		}
		
		//alterar aqui para alterar os dias com o plano escolhido
		subscription.setEndDate(subscription.getStartDate().plusDays(30));
		
		return subscriptionRepository.save(subscription);
	}
	
	public void cancelSubscription (User user) {
		Optional<Subscription> checkSubscription = subscriptionRepository.findByUserAndStatus(user, SubscriptionStatus.ACTIVATED);
		
		if (checkSubscription.isEmpty()) throw new RuntimeException("O usuário não possui assinatura ativa para cancelamento");
		
		Subscription subscription = checkSubscription.get();
		subscription.setStatus(SubscriptionStatus.DISABLED);
		subscriptionRepository.save(subscription);
	}
	
}
