package io.praksa.intensproject.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
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
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import io.praksa.intensproject.models.Candidate;
import io.praksa.intensproject.models.Skill;
import io.praksa.intensproject.services.CandidateService;
import io.praksa.intensproject.services.SkillService;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
class SkillControllerTest {

	  @Autowired
	  private MockMvc mockMvc;
	  
	  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	  
	  @Autowired
	  private WebApplicationContext wac;
	  
	  @Mock
	  SkillService skillService;
	  
	  @InjectMocks
	  SkillController skillController;
	  
	  @Before
	  public void setup() {
		  
	      MockitoAnnotations.initMocks(this);
	      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
	  
	      List<Skill> lista = new ArrayList<>();
		  Skill s = new Skill(1l, "Java");
		  
		  when(skillService.getAllSkills()).thenReturn(lista);
		  when(skillService.findSkillById((long) 1)).thenReturn(s);
	  }
	
	  @Test
	  public void getAllSkillsTest() throws Exception { 
	      this.mockMvc.perform(get("/skills").accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	  }
	  
	   	/*@Test
		@Transactional
		@Rollback(true)
		public void addSkillTest() throws Exception{

			String json = "{\r\n" + 
					"\"id\": -1,\r\n" + 
					"\"name\": \"JavaA\"\r\n" + 
					"}";	
			
			mockMvc.perform(post("/add/skill").contentType(contentType).content(json)).andExpect(status().isCreated());
			
		}
	   	
		@Test
		@Transactional
		@Rollback(true)
		public void updateSkillTest() throws Exception{
			
			String json = "{\r\n" + 
					"\"id\": 1,\r\n" + 
					"\"name\": \"Java\"\r\n" + 
					"}";	
			
			
			this.mockMvc.perform(put("/update/candidate/1").contentType(contentType).content(json)).andExpect(status().isOk());
		
		}*/

}
