package it.vwgroup.il.cap.controller;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import it.vwgroup.il.cap.service.CapService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CapControllerTest {
	
	@InjectMocks
	CapController capController;
	
	@Mock
	CapService capService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(capController).build();
	}
	
	@Test
	public void shouldReturnCapInformationById() throws Exception {
		mockMvc.perform(get("/T_CN_CAP/{id}")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content("25014"))
		    .andExpect(status().isOk());
	}
	
	@Test
	public void shouldReturnCapInformationByCode() throws Exception {
		mockMvc.perform(get("/T_CN_CAP/{code}")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content("250"))
		    .andExpect(status().isOk());
	}
}
