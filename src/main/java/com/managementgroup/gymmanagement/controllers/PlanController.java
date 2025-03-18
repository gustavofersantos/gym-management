package com.managementgroup.gymmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.managementgroup.gymmanagement.dto.PlanDto;
import com.managementgroup.gymmanagement.services.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController {

	@Autowired
	private PlanService planService;
	
	@GetMapping
	public ResponseEntity<List<PlanDto>> getAllPlans() {
		return ResponseEntity.ok(planService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlanDto> getPlanById(@PathVariable Long id) {
		return ResponseEntity.ok(planService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<PlanDto> createPlan(@RequestBody PlanDto planDto) {
		PlanDto newPlan = planService.createPlan(planDto);
		return ResponseEntity.status(201).body(newPlan);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PlanDto> updatePlan(@PathVariable Long id, @RequestBody PlanDto planDto) {
		return ResponseEntity.ok(planService.updatePlan(id, planDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
		planService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
