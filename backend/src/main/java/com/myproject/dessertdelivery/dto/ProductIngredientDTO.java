package com.myproject.dessertdelivery.dto;

import java.io.Serializable;

import com.myproject.dessertdelivery.entities.Ingredient;
import com.myproject.dessertdelivery.entities.Product;
import com.myproject.dessertdelivery.entities.ProductIngredient;
import com.myproject.dessertdelivery.entities.PK.ProductIngredientPK;

public class ProductIngredientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private ProductIngredientPK id = new ProductIngredientPK();
	private Double quantity;
	
	public ProductIngredientDTO() {
	}

	public ProductIngredientDTO(Product product, Ingredient ingredient, Double quantity) {
		id.setProduct(product);
		id.setIngredient(ingredient);
		this.quantity = quantity;
	}
	
	public ProductIngredientDTO(ProductIngredient entity) {
		id.setProduct(entity.getId().getProduct());
		id.setIngredient(entity.getId().getIngredient());
		quantity = entity.getQuantity();
	}

	public ProductIngredientPK getId() {
		return id;
	}

	public void setId(ProductIngredientPK id) {
		this.id = id;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
}
