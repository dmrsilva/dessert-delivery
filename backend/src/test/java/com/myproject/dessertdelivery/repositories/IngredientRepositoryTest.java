package com.myproject.dessertdelivery.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.myproject.dessertdelivery.entities.Ingredient;
import com.myproject.dessertdelivery.tests.Factory;

@DataJpaTest
public class IngredientRepositoryTest {

	@Autowired
	private IngredientRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countTotalIngredients;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalIngredients = 6L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		
		Ingredient ingredient  = Factory.createIngredient();
		ingredient.setId(null);
		
		ingredient = repository.save(ingredient);
		
		Assertions.assertNotNull(ingredient.getId());
		Assertions.assertEquals(countTotalIngredients + 1, ingredient.getId());
		
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalIngredientWhenIdExists() {
		
		Optional<Ingredient> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
		
	}
	
	@Test
	public void findByIdShouldEmnptyOptionalWhenIdDoesNotExist() {

		Optional<Ingredient> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());

	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(existingId);
		
		Optional<Ingredient> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
		
	}
	
	
}
