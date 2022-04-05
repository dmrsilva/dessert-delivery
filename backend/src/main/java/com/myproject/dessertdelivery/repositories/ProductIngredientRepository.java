package com.myproject.dessertdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.dessertdelivery.entities.Ingredient;
import com.myproject.dessertdelivery.entities.ProductIngredientPK;

@Repository
public interface ProductIngredientRepository extends JpaRepository<Ingredient, ProductIngredientPK> {

}
