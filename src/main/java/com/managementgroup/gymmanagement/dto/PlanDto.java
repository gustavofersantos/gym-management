package com.managementgroup.gymmanagement.dto;

import com.managementgroup.gymmanagement.entities.Plan;

public class PlanDto {

	private Long id;
	private String name;
	private Double price;
	private Integer durationInMonths;
	
	public PlanDto() {
	}

	public PlanDto(Long id, String name, Double price, Integer durationInMonths) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.durationInMonths = durationInMonths;
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

	public Integer getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(Integer durationInMonths) {
		this.durationInMonths = durationInMonths;
	}
	

	public static PlanDto fromPlanDto(Plan plan) {
		return new PlanDto(plan.getId(), plan.getName(), plan.getPrice(), plan.getDurationInMonths());
	}
}
