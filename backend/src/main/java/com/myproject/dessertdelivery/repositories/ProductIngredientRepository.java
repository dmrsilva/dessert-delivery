package com.myproject.dessertdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.dessertdelivery.entities.ProductIngredient;
import com.myproject.dessertdelivery.entities.PK.ProductIngredientPK;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, ProductIngredientPK> {

}
