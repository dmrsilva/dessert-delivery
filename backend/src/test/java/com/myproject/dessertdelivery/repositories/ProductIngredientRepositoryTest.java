package com.myproject.dessertdelivery.repositories;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.myproject.dessertdelivery.entities.ProductIngredient;
import com.myproject.dessertdelivery.tests.Factory;

@DataJpaTest
public class ProductIngredientRepositoryTest {

	@Autowired
	private ProductIngredientRepository repository;
	
	private ProductIngredient productIngredient;
	private ProductIngredient productIngredientEmpty;
	
	@BeforeEach
	void setUp() throws Exception {
		productIngredient = Factory.createProductIngredient();
		productIngredientEmpty = new ProductIngredient();
	}
	
	@Test
	public void saveShouldPersistWithProductIngredientPKWhenIdIsNotNull() {
		
		productIngredient = repository.save(productIngredient);
		
		Assertions.assertNotNull(productIngredient);
		
		Assertions.assertEquals(1L, productIngredient.getId().getProduct().getId());
		Assertions.assertEquals(1L, productIngredient.getId().getIngredient().getId());
		
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalProductIngredientWhenIdExists() {

		Optional<ProductIngredient> result = repository.findById(productIngredient.getId());
		
		Assertions.assertTrue(result.isPresent());
		
		Assertions.assertEquals(1L, result.get().getId().getProduct().getId());
		Assertions.assertEquals(1L, result.get().getId().getIngredient().getId());
		
	}
	
	@Test
	public void findByIdShouldThrowNoSuchElementExceptionWhenProductIdAndIngredientIdDoesNotExist() {
		
		Optional<ProductIngredient> result = repository.findById(productIngredientEmpty.getId());
		
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			result.get().getId().getProduct();
		});

		Assertions.assertThrows(NoSuchElementException.class, () -> {
			result.get().getId().getIngredient();
		});

	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(productIngredient.getId());
		
		Optional<ProductIngredient> result = repository.findById(productIngredientEmpty.getId());
		Assertions.assertTrue(result.isEmpty());
		
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(productIngredientEmpty.getId());
		});		
		
	}
	
}
