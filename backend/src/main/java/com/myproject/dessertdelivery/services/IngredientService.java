package com.myproject.dessertdelivery.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dessertdelivery.dto.IngredientDTO;
import com.myproject.dessertdelivery.entities.Ingredient;
import com.myproject.dessertdelivery.repositories.IngredientRepository;
import com.myproject.dessertdelivery.services.exceptions.ResourceNotFoundException;

@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository repository;
	
	@Transactional
	public IngredientDTO insert(IngredientDTO dto) {
		Ingredient entity = new Ingredient();
		copyDtoEntity(dto, entity);
		entity = repository.save(entity);		
		return new IngredientDTO(entity);
	}
	
	@Transactional
	public IngredientDTO update(Long id, IngredientDTO dto) {
		try {
			Ingredient entity = repository.getById(id);
			copyDtoEntity(dto, entity);
			entity = repository.save(entity);
			return new IngredientDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Transactional(readOnly = true)
	public IngredientDTO findById(Long id) {
		Optional<Ingredient> obj = repository.findById(id);
		Ingredient entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new IngredientDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<IngredientDTO> findAll() {
		List<Ingredient> list = repository.findAll();
		return list.stream().map(x -> new IngredientDTO(x)).collect(Collectors.toList());
	}
	
	private void copyDtoEntity(IngredientDTO dto, Ingredient entity) {
		entity.setName(dto.getName());
		entity.setQuantityInStock(dto.getQuantityInStock());
		entity.setUnitOfMeasure(dto.getUnitOfMeasure());
	}

}
