package com.myproject.dessertdelivery.dto;

import java.io.Serializable;

public class ProductIngredientPKDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private ProductDTO product;
	private IngredientDTO ingredient;
	
	public ProductIngredientPKDTO() {
	}

	public ProductIngredientPKDTO(ProductDTO product, IngredientDTO ingredient) {
		this.product = product;
		this.ingredient = ingredient;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public IngredientDTO getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientDTO ingredient) {
		this.ingredient = ingredient;
	}

}
