package com.jarmison.dev.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarmison.dev.api.core.entity.PersonEntity;
import com.jarmison.dev.api.request.PersonRequest;
import com.jarmison.dev.api.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PersonService personService;

	private PersonEntity personEntity;

	@BeforeEach
	void setUp() {
		personEntity = new PersonEntity();
		personEntity.setId(1L);
		personEntity.setNome("Jarmison Paiva");
		personEntity.setCpf("59016579001");
		personEntity.setEmail("jarmisondev@example.com");
		personEntity.setDataNascimento(LocalDate.of(2004, 1, 1));
	}

	@Test
	void shouldAddPerson() throws Exception {
		PersonRequest personRequest = new PersonRequest();
		personRequest.setNome("Jarmison Paiva");
		personRequest.setCpf("59016579001");
		personRequest.setEmail("jarmisondev@example.com");
		personRequest.setDataNascimento(LocalDate.of(2004, 1, 1));

		when(personService.save(any(PersonEntity.class))).thenReturn(personEntity);

		var result = mockMvc.perform(post("/persons")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(personRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(personEntity.getId()))
				.andExpect(jsonPath("$.nome").value(personEntity.getNome()))
				.andExpect(jsonPath("$.cpf").value(personEntity.getCpf()))
				.andExpect(jsonPath("$.email").value(personEntity.getEmail()))
				.andReturn();

		System.out.println("Response Body: " + result.getResponse().getContentAsString());
	}

	@Test
	void shouldGetAllPersons() throws Exception {
		when(personService.findAll()).thenReturn(Collections.singletonList(personEntity));

		var result = mockMvc.perform(get("/persons"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(personEntity.getId()))
				.andExpect(jsonPath("$[0].nome").value(personEntity.getNome()))
				.andExpect(jsonPath("$[0].cpf").value(personEntity.getCpf()))
				.andExpect(jsonPath("$[0].email").value(personEntity.getEmail()))
				.andReturn();

		System.out.println("Response Body: " + result.getResponse().getContentAsString());
	}

	@Test
	void shouldGetPersonById() throws Exception {
		when(personService.findById(personEntity.getId())).thenReturn(personEntity);

		var result = mockMvc.perform(get("/persons/{id}", personEntity.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(personEntity.getId()))
				.andExpect(jsonPath("$.nome").value(personEntity.getNome()))
				.andExpect(jsonPath("$.cpf").value(personEntity.getCpf()))
				.andExpect(jsonPath("$.email").value(personEntity.getEmail()))
				.andReturn();

		System.out.println("Response Body: " + result.getResponse().getContentAsString());
	}

	@Test
	void shouldUpdatePerson() throws Exception {
		PersonRequest personRequest = new PersonRequest();
		personRequest.setNome("Jarmison Paiva");
		personRequest.setCpf("59016579001");
		personRequest.setEmail("jarmisondev@example.com");
		personRequest.setDataNascimento(LocalDate.of(2004, 1, 1));

		when(personService.findById(personEntity.getId())).thenReturn(personEntity);
		when(personService.save(any(PersonEntity.class))).thenReturn(personEntity);

		var result = mockMvc.perform(put("/persons/{id}", personEntity.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(personRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(personEntity.getId()))
				.andExpect(jsonPath("$.nome").value(personEntity.getNome()))
				.andExpect(jsonPath("$.cpf").value(personEntity.getCpf()))
				.andExpect(jsonPath("$.email").value(personEntity.getEmail()))
				.andReturn();
		System.out.println("Response Body: " + result.getResponse().getContentAsString());
	}

	@Test
	void shouldDeletePerson() throws Exception {
		doNothing().when(personService).deleteById(personEntity.getId());

		var result = mockMvc.perform(delete("/persons/{id}", personEntity.getId()))
				.andExpect(status().isOk())
				.andReturn();

		System.out.println("Response Status: " + result.getResponse().getStatus());
		System.out.println("############################ TESTES DE API ############################################");
	}
}
