package io.praksa.intensproject.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import io.praksa.intensproject.models.Candidate;
import io.praksa.intensproject.repositories.CandidateRepository;
import io.praksa.intensproject.services.CandidateService;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
class CandidateControllerTest {

	  @Autowired
	  private MockMvc mockMvc;
	  
	  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	  
	  @Autowired
	  private WebApplicationContext wac;
	  
	  @Mock
	  CandidateService candidateService;
	  
	  @InjectMocks
	  CandidateController candidateController;

	  @Before
	  public void setup() {
		  
		  MockitoAnnotations.initMocks(this);
	      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
		  
		  List<Candidate> lista = new ArrayList<>();
		  Candidate c2 = new Candidate();
		  c2.setId(2l);
		  c2.setFullName("Mirko Mirkovic");
		  c2.setTelNumber("064/789654");
		  c2.setEmail("mirko@mail.com");
		  lista.add(c2);  
		  
		  when(candidateService.getAllCandidates()).thenReturn(lista);
		  when(candidateService.findCandidateById((long) 1)).thenReturn(c2);
	  }

	  @Test
	  public void getAllCandidatesTest() throws Exception { 
	      this.mockMvc.perform(get("/candidates").accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	  }
	  
   	/*@Test
	@Transactional
	@Rollback(true)
	public void addCandidateTest() throws Exception{

		String json = "{\r\n" + 
				"\"id\": -1,\r\n" + 
				"\"fullName\": \"Marko Katic\",\r\n" +
				"\"dateOfBirth\": \"1996-08-08\",\r\n" +
				"\"telNumber\": \"064/789654\",\r\n" +
				"\"email\": \"marko22@mail.com\",\r\n" + 
				"\"skills\": []\r\n" + 
				"}";	
		
		mockMvc.perform(post("/add/candidate").contentType(contentType).content(json)).andExpect(status().isCreated());
		
	}*/
		
	/*@Test
	@Transactional
	@Rollback(true)
	public void updateCandidateTest() throws Exception{
		
		String json = "{\r\n" + 
				"\"id\": 1,\r\n" + 
				"\"fullName\": \"Marko Katic\",\r\n" +
				"\"dateOfBirth\": \"1996-08-08\",\r\n" +
				"\"telNumber\": \"064/789654\",\r\n" +
				"\"email\": \"marko@mail.com\",\r\n" + 
				"\"skills\": []\r\n" + 
				"}";
		
		
		this.mockMvc.perform(put("/update/candidate/1").contentType(contentType).content(json)).andExpect(status().isOk());
	
	}*/
		
	@Test
	public void searchTest() throws Exception{
		
		String json = "{\r\n" + 
				"\"fullName\": \"Marko Katic\",\r\n" +
				"\"skills\": []\r\n"+
				"}";
		
		this.mockMvc.perform(post("/search").contentType(contentType).content(json)).andExpect(status().isOk());
	
	}
		
	@Test
	public void notAquiredSkillsTest() throws Exception{			
		this.mockMvc.perform(get("/notAquiredSkills/1")).andExpect(status().isOk());	
	}
		
		
		
}
