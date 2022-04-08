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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.myproject.dessertdelivery.dto.ProductIngredientDTO;
import com.myproject.dessertdelivery.entities.ProductIngredient;
import com.myproject.dessertdelivery.repositories.ProductIngredientRepository;
import com.myproject.dessertdelivery.services.exceptions.ResourceNotFoundException;
import com.myproject.dessertdelivery.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ProductIngredientServiceTests {

	@InjectMocks
	private ProductIngredientService service;
	
	@Mock
	private ProductIngredientRepository repository;
	
	private ProductIngredient productIngredient;
	private ProductIngredient productIngredientNonExists;
	private ProductIngredientDTO productIngredientDTO;
	private ProductIngredientDTO productIngredientNonExistsDTO;
	private List<ProductIngredient> list;
	
	@BeforeEach
	void setUp() throws Exception {
		
		productIngredient = Factory.createProductIngredient();
		productIngredientDTO = Factory.createProductIngredientDTO();
		productIngredientNonExists = new ProductIngredient();
		productIngredientNonExistsDTO = new ProductIngredientDTO();
		list = new ArrayList<>();
		list.add(productIngredient);
		
		
		Mockito.when(repository.save(any())).thenReturn(productIngredient);
		
		Mockito.when(repository.getById(productIngredient.getId())).thenReturn(productIngredient);
		
		Mockito.when(repository.getById(productIngredientNonExists.getId())).thenThrow(EntityNotFoundException.class);
		
		Mockito.doNothing().when(repository).deleteById(productIngredient.getId());
		
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(productIngredientNonExists.getId());
		
		Mockito.when(repository.findById(productIngredient.getId())).thenReturn(Optional.of(productIngredient));
		
		Mockito.when(repository.findById(productIngredientNonExists.getId())).thenReturn(Optional.empty());
		
		Mockito.when(repository.findAll()).thenReturn(list);		
	}


	@Test
	public void insertShouldReturnProductIngredientDTO() {
		
		ProductIngredientDTO result = service.insert(productIngredientDTO);
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).save(any());
		
	}
	
	@Test
	public void updateShouldReturnProductIngredientDTOWhenIdDoesExist() {
		
		ProductIngredientDTO result = service.update(productIngredientDTO);
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).getById(productIngredient.getId());
		Mockito.verify(repository).save(any());		
		
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(productIngredientNonExistsDTO);
		});
		
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(productIngredientDTO);
		});
		
		Mockito.verify(repository).deleteById(productIngredient.getId());		
		
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(productIngredientNonExistsDTO);
		});
		
		Mockito.verify(repository).deleteById(productIngredientNonExists.getId());
		
	}
	
	@Test
	public void findByIdReturnProductIngredientDTOWhenIdExists() {
		
		ProductIngredientDTO result = service.findById(productIngredientDTO);
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).findById(productIngredient.getId());
		
	}
	
	@Test
	public void findByIdThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(productIngredientNonExistsDTO);
		});
		
	}
	
	@Test
	public void findAllShouldReturnList() {
		
		List<ProductIngredientDTO> list = service.findAll();
		
		Assertions.assertNotNull(list);
		
		Mockito.verify(repository).findAll();
		
	}	
	
}
