package com.myproject.dessertdelivery.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myproject.dessertdelivery.entities.PK.ProductIngredientPK;

@Entity
@Table(name = "tb_product_ingredient")
public class ProductIngredient {
	
	@EmbeddedId
	private ProductIngredientPK id = new ProductIngredientPK();	
	
	private Double quantity;
	
	public ProductIngredient() {
	}

	public ProductIngredient(Product product, Ingredient ingredient, Double quantity) {
		id.setProduct(product);
		id.setIngredient(ingredient);
		this.quantity = quantity;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductIngredient other = (ProductIngredient) obj;
		return Objects.equals(id, other.id);
	}

}
