package com.myproject.dessertdelivery.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ProductIngredientPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public ProductIngredientPK() {
	}

	public ProductIngredientPK(Ingredient ingredient, Product product) {
		this.ingredient = ingredient;
		this.product = product;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredient, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductIngredientPK other = (ProductIngredientPK) obj;
		return Objects.equals(ingredient, other.ingredient) && Objects.equals(product, other.product);
	}
	
}
