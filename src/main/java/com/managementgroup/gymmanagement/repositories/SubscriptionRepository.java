package com.managementgroup.gymmanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managementgroup.gymmanagement.entities.Subscription;
import com.managementgroup.gymmanagement.entities.User;
import com.managementgroup.gymmanagement.entities.enums.SubscriptionStatus;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	Optional<Subscription> findByUserAndStatus(User user, SubscriptionStatus subscriptionStatus);
}
