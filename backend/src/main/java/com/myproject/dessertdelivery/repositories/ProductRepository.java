package com.myproject.dessertdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.dessertdelivery.entities.Ingredient;

@Repository
public interface ProductRepository extends JpaRepository<Ingredient, Long> {

}
