package com.managementgroup.gymmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.managementgroup.gymmanagement.dto.SubscriptionDto;
import com.managementgroup.gymmanagement.services.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/create")
    public ResponseEntity<SubscriptionDto> createSubscription(@RequestParam Long userId, @RequestParam Long planId) {
        SubscriptionDto subscriptionDto = subscriptionService.createSubscription(userId, planId);
        return ResponseEntity.ok(subscriptionDto);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelSubscription(@RequestParam Long userId) {
        subscriptionService.cancelSubscription(userId);
        return ResponseEntity.ok("Assinatura cancelada com sucesso.");
    }
}