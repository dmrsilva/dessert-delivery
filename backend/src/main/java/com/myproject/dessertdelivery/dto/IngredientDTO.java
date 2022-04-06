package com.myproject.dessertdelivery.dto;

import java.io.Serializable;

import com.myproject.dessertdelivery.entities.Ingredient;

public class IngredientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String unitOfMeasure;
	private Double quantityInStock;
	
	public IngredientDTO() {
	}

	public IngredientDTO(Long id, String name, String unitOfMeasure, Double quantityInStock) {
		this.id = id;
		this.name = name;
		this.unitOfMeasure = unitOfMeasure;
		this.quantityInStock = quantityInStock;
	}
	
	public IngredientDTO(Ingredient entity) {
		id = entity.getId();
		name = entity.getName();
		unitOfMeasure = entity.getUnitOfMeasure();
		quantityInStock = entity.getQuantityInStock();
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

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Double getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Double quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	
}
