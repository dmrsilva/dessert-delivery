package com.myproject.dessertdelivery.dto;

import java.io.Serializable;

public class ProductIngredientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private ProductIngredientPKDTO id = new ProductIngredientPKDTO();
	private Double quantity;
	
	public ProductIngredientDTO() {
	}

	public ProductIngredientDTO(ProductDTO product, IngredientDTO ingredient, Double quantity) {
		id.setProduct(product);
		id.setIngredient(ingredient);
		this.quantity = quantity;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
}
