package com.myproject.dessertdelivery.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.myproject.dessertdelivery.dto.ProductIngredientDTO;
import com.myproject.dessertdelivery.entities.ProductIngredient;
import com.myproject.dessertdelivery.repositories.ProductIngredientRepository;
import com.myproject.dessertdelivery.services.exceptions.ResourceNotFoundException;

@Service
public class ProductIngredientService {

	@Autowired
	private ProductIngredientRepository repository;
	
	public ProductIngredientDTO insert(ProductIngredientDTO dto) {
		ProductIngredient entity = new ProductIngredient();
		entity.setId(dto.getId());
		entity.setQuantity(dto.getQuantity());
		entity = repository.save(entity);
		return new ProductIngredientDTO(entity);
	}
	
	public ProductIngredientDTO update(ProductIngredientDTO dto) {
		try {
			ProductIngredient entity = repository.getById(dto.getId());
			entity.setQuantity(dto.getQuantity());
			entity = repository.save(entity);
			return new ProductIngredientDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + dto.getId());
		}
	}
	
	public void delete (ProductIngredientDTO id) {
		try {
			repository.deleteById(id.getId());
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id.getId());
		}		
	}
	
	public ProductIngredientDTO findById(ProductIngredientDTO id) {
		
		Optional<ProductIngredient> obj = repository.findById(id.getId());
		ProductIngredient entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProductIngredientDTO(entity);		
	}
	
	public List<ProductIngredientDTO> findAll() {
		List<ProductIngredient> list = repository.findAll();
		return list.stream().map(x -> new ProductIngredientDTO(x)).collect(Collectors.toList());
	}
	
}
