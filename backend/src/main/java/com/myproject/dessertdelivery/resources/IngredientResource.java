package com.myproject.dessertdelivery.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.dessertdelivery.dto.IngredientDTO;
import com.myproject.dessertdelivery.services.IngredientService;

@RestController
@RequestMapping(value = "/ingredients")
public class IngredientResource {
	
	@Autowired
	private IngredientService service;
	
	@GetMapping
	public ResponseEntity<List<IngredientDTO>> findAll() {
		
		List<IngredientDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	public ResponseEntity<IngredientDTO> findById(@PathVariable Long id) {
		return null;
	}
	
	public ResponseEntity<IngredientDTO> insert(@RequestBody IngredientDTO dto) {
		return null;
	}
	
	public ResponseEntity<IngredientDTO> update(@PathVariable Long id, @RequestBody IngredientDTO dto) {
		return null;
	}
	
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		return null;
	}

}
