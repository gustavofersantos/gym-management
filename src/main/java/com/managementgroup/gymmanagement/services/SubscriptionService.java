package com.managementgroup.gymmanagement.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managementgroup.gymmanagement.dto.SubscriptionDto;
import com.managementgroup.gymmanagement.entities.Plan;
import com.managementgroup.gymmanagement.entities.Subscription;
import com.managementgroup.gymmanagement.entities.User;
import com.managementgroup.gymmanagement.entities.enums.SubscriptionStatus;
import com.managementgroup.gymmanagement.repositories.PlanRepository;
import com.managementgroup.gymmanagement.repositories.SubscriptionRepository;
import com.managementgroup.gymmanagement.repositories.UserRepository;

@Service
public class SubscriptionService {
    
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PlanRepository planRepository;
    
    public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
        User user = userRepository.findByCpf(subscriptionDto.getUserCpf())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Plan plan = planRepository.findByName(subscriptionDto.getPlanName())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        Subscription activeSubscription = subscriptionRepository.findByUserAndStatus(user, SubscriptionStatus.ACTIVATED)
                .orElse(null);
        
        if (activeSubscription != null) {
            throw new RuntimeException("O usuário já possui uma assinatura ativa");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusDays(plan.getDurationInDays()));
        subscription.setStatus(SubscriptionStatus.DISABLED);

        return SubscriptionDto.fromSubscription(subscriptionRepository.save(subscription));
    }
    
    public SubscriptionDto cancelSubscription(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Subscription subscription = subscriptionRepository.findByUserAndStatus(user, SubscriptionStatus.ACTIVATED)
                .orElseThrow(() -> new RuntimeException("O usuário não possui assinatura ativa para cancelamento"));
        
        subscription.setStatus(SubscriptionStatus.DISABLED);
        return SubscriptionDto.fromSubscription(subscriptionRepository.save(subscription));
    }
}
