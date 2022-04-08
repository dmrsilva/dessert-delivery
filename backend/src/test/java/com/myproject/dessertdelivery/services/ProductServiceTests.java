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

import com.myproject.dessertdelivery.dto.ProductDTO;
import com.myproject.dessertdelivery.entities.Product;
import com.myproject.dessertdelivery.repositories.ProductRepository;
import com.myproject.dessertdelivery.services.exceptions.ResourceNotFoundException;
import com.myproject.dessertdelivery.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	private Product product;
	private ProductDTO productDTO;
	private List<Product> list;
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 2L;
		product = Factory.createProduct();
		productDTO = Factory.createProductDTO();
		list = new ArrayList<>();
		list.add(product);
		
		
		Mockito.when(repository.save(any())).thenReturn(product);
		
		Mockito.when(repository.getById(existingId)).thenReturn(product);
		
		Mockito.when(repository.getById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
		Mockito.doNothing().when(repository).deleteById(existingId);
		
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
		
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(repository.findAll()).thenReturn(list);		
	}
	
	@Test
	public void insertShouldReturnProductDTO() {
		
		ProductDTO result = service.insert(productDTO);
		
		Assertions.assertNotNull(result);

		Mockito.verify(repository).save(any());

	}
	
	@Test
	public void updateShouldReturnProductDTOWhenIdDoesExist() {
		
		ProductDTO result = service.update(existingId, productDTO);
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).getById(existingId);
		Mockito.verify(repository).save(any());
		
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, productDTO);
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
	public void findByIdReturnProductDTOWhenIdExists() {
		
		ProductDTO result = service.findById(existingId);
		
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
		
		List<ProductDTO> result = service.findAll();
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).findAll();
		
	}
	
}
