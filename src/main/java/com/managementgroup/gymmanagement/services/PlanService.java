package com.managementgroup.gymmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managementgroup.gymmanagement.entities.Plan;
import com.managementgroup.gymmanagement.repositories.PlanRepository;
import com.managementgroup.gymmanagement.services.exceptions.ObjectNotFoundException;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepository planRepository;
	
	public List<Plan> findAll() {
		return planRepository.findAll();
	}
	
	public Plan findById(Long id) {
		return planRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Plano não encontrado"));
	}
	
	public Plan createPlan(Plan plan) {
		return planRepository.save(plan);
	}
	
	private void updateData(Plan plan) {
		plan.setName(plan.getName());
		plan.setPrice(plan.getPrice());	
	}
	
	public void deleteById(Long id) {
		planRepository.deleteById(id);
	}
}
