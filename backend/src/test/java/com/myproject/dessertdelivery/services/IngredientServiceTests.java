package com.myproject.dessertdelivery.services;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.myproject.dessertdelivery.dto.IngredientDTO;
import com.myproject.dessertdelivery.entities.Ingredient;
import com.myproject.dessertdelivery.repositories.IngredientRepository;
import com.myproject.dessertdelivery.services.exceptions.DatabaseException;
import com.myproject.dessertdelivery.services.exceptions.ResourceNotFoundException;
import com.myproject.dessertdelivery.tests.Factory;

@ExtendWith(SpringExtension.class)
public class IngredientServiceTests {

	@InjectMocks
	private IngredientService service;
	
	@Mock
	private IngredientRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private Ingredient ingredient;
	private IngredientDTO ingredientDTO;
	private List<Ingredient> list;
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		ingredientDTO = Factory.createIngredientDTO();
		ingredient = Factory.createIngredient();
		list = new ArrayList<>();
		list.add(ingredient);
		
		
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(ingredient);
		
		Mockito.when(repository.getById(existingId)).thenReturn(ingredient);
		
		Mockito.when(repository.getById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
		Mockito.doNothing().when(repository).deleteById(existingId);
		
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(ingredient));
		
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(repository.findAll()).thenReturn(list);
		
	}
	
	@Test
	public void insertShouldReturnIngredientDTO() {
		
		IngredientDTO result = service.insert(ingredientDTO);
		
		Assertions.assertNotNull(result);

		Mockito.verify(repository).save(any());
		
	}
	
	@Test
	public void updateShouldReturnIngredientDTOWhenIdDoesExist() {
		
		IngredientDTO result = service.update(existingId, ingredientDTO);
		
		Assertions.assertNotNull(result);

		Mockito.verify(repository).getById(existingId);
		Mockito.verify(repository).save(any());

	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, ingredientDTO);
		});
		
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(repository).deleteById(existingId);
		
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		
		Mockito.verify(repository).deleteById(nonExistingId);
		
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
		
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});
		
		Mockito.verify(repository).deleteById(dependentId);
		
	}
	
	@Test
	public void findByIdReturnIngredientDTOWhenIdExists() {
		
		IngredientDTO result = service.findById(existingId);
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).findById(existingId);
		
	}
	
	@Test
	public void findByIdThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
		
	}
	
	@Test
	public void findAllShouldReturnList() {
		
		List<IngredientDTO> result = service.findAll();
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).findAll();
		
	}
	
	
	
}
