package com.managementgroup.gymmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.managementgroup.gymmanagement.dto.SubscriptionDto;
import com.managementgroup.gymmanagement.services.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/create")
    public ResponseEntity<SubscriptionDto> createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        SubscriptionDto createdSubscription = subscriptionService.createSubscription(subscriptionDto);
        return ResponseEntity.ok(createdSubscription);
        
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelSubscription(@RequestBody Long userId) {
        subscriptionService.cancelSubscription(userId);
        return ResponseEntity.ok("Assinatura cancelada com sucesso.");
    }
}