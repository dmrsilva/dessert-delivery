package com.myproject.dessertdelivery.tests;

import com.myproject.dessertdelivery.dto.IngredientDTO;
import com.myproject.dessertdelivery.entities.Ingredient;
import com.myproject.dessertdelivery.entities.Product;
import com.myproject.dessertdelivery.entities.ProductIngredient;

public class Factory {
	
	public static Ingredient createIngredient() {
		Ingredient ingredient = new Ingredient(1L, "Fermento", "kg", 5.0);
		return ingredient;
	}
	
	public static Product createProduct() {
		Product product = new Product(1L, "Pudim de leite", 55.0);
		return product;
	}
	
	public static ProductIngredient createProductIngredient() {
		Product product = createProduct();
		Ingredient ingredient = createIngredient();
		
		ProductIngredient productIngredient = new ProductIngredient(product, ingredient, 0.8);
		return productIngredient;
	}
	
	public static IngredientDTO createIngredientDTO() {
		Ingredient ingredient = createIngredient();
		return new IngredientDTO(ingredient);
	}
	

}
