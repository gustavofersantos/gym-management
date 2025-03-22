package com.managementgroup.gymmanagement.dto;

import com.managementgroup.gymmanagement.entities.Plan;

import jakarta.validation.constraints.NotNull;

public class PlanDto {

	private Long id;
	
	@NotNull
	private String name;
	
	private String description;
	
	@NotNull
	private Double price;
	
	private Integer durationInDays;

	public PlanDto() {
	}

	public PlanDto(Long id, String name, String description, Double price, Integer durationInDays) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.durationInDays = durationInDays;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDurationInDays() {
		return durationInDays;
	}

	public void setDurationInDays(Integer durationInDays) {
		this.durationInDays = durationInDays;
	}

	public static PlanDto fromEntity(Plan plan) {
		return new PlanDto(plan.getId(), plan.getName(), plan.getDescription(), plan.getPrice(), plan.getDurationInDays());
	}

	public static Plan toEntity(PlanDto planDto) {
		Plan plan = new Plan();
		plan.setId(planDto.getId());
		plan.setName(planDto.getName());
		plan.setDescription(planDto.getDescription());
		plan.setPrice(planDto.getPrice());
		plan.setDurationInDays(planDto.getDurationInDays());
		return plan;
	}
}
