package com.managementgroup.gymmanagement.dto;

import com.managementgroup.gymmanagement.entities.Plan;

public class PlanDto {

	private Long id;
	private String name;
	private Double price;
	private Integer durationInDays;

	public PlanDto() {
	}

	public PlanDto(Long id, String name, Double price, Integer durationInDays) {
		super();
		this.id = id;
		this.name = name;
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
		return new PlanDto(plan.getId(), plan.getName(), plan.getPrice(), plan.getDurationInDays());
	}

	public static Plan toEntity(PlanDto dto) {
		Plan plan = new Plan();
		plan.setId(dto.getId());
		plan.setName(dto.getName());
		plan.setPrice(dto.getPrice());
		return plan;
	}
}
