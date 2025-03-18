package com.managementgroup.gymmanagement.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managementgroup.gymmanagement.dto.PlanDto;
import com.managementgroup.gymmanagement.entities.Plan;
import com.managementgroup.gymmanagement.repositories.PlanRepository;
import com.managementgroup.gymmanagement.services.exceptions.ObjectNotFoundException;

@Service
public class PlanService {

	@Autowired
	private PlanRepository planRepository;

	public List<PlanDto> findAll() {
        return planRepository.findAll().stream()
                .map(PlanDto::fromEntity)
                .collect(Collectors.toList());
    }

    public PlanDto findById(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Plano não encontrado"));
        return PlanDto.fromEntity(plan);
    }

    public PlanDto createPlan(PlanDto planDto) {
        Plan plan = PlanDto.toEntity(planDto);
        return PlanDto.fromEntity(planRepository.save(plan));
    }

    public PlanDto updatePlan(Long id, PlanDto planDto) {
        Plan existingPlan = planRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Plano não encontrado"));

        existingPlan.setName(planDto.getName());
        existingPlan.setPrice(planDto.getPrice());

        return PlanDto.fromEntity(planRepository.save(existingPlan));
    }

    public void deleteById(Long id) {
        planRepository.deleteById(id);
    }
}