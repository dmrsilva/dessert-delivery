package com.myproject.dessertdelivery.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.dessertdelivery.dto.IngredientDTO;
import com.myproject.dessertdelivery.services.IngredientService;
import com.myproject.dessertdelivery.tests.Factory;

@WebMvcTest(IngredientResource.class)
public class IngredientResourceTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IngredientService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private long existingId;
	private long nonExistingId;
	private IngredientDTO ingredientDTO;
	private List<IngredientDTO> list;
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 2L;
		
		ingredientDTO = Factory.createIngredientDTO();
	
		when(service.findAll()).thenReturn(list);		
		
	}
	
	@Test
	public void findAllShouldReturnList() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(get("/ingredients")
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		
	}
	
	@Test
	public void findByIdShouldReturnIngredientDTOWhenIdExists() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(get("/ingredients/{id}", existingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.unitOfMeasure").exists());
		result.andExpect(jsonPath("$.quantityInStock").exists());

	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(get("/ingredients/{id}", nonExistingId)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void insertShouldReturnCreateAndProductDTO() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(ingredientDTO);
		
		ResultActions result = 
				mockMvc.perform(post("/ingredients")
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.unitOfMeasure").exists());
		result.andExpect(jsonPath("$.quantityInStock").exists());
		
	}
	
	@Test
	public void updateShouldReturnIngredientDTOWhenIdExists() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(ingredientDTO);
		
		ResultActions result = 
				mockMvc.perform(put("/ingredients/{id}", existingId)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.unitOfMeasure").exists());
		result.andExpect(jsonPath("$.quantityInStock").exists());
		
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNoExists() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(ingredientDTO);
		
		ResultActions result = 
				mockMvc.perform(put("/ingredients/{id}", nonExistingId)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenidExists() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(delete("/ingredients/{id}", existingId));
		
		result.andExpect(status().isNoContent());
				
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(delete("/ingredients/{id}", nonExistingId));
		
		result.andExpect(status().isNotFound());
		
	}

}
