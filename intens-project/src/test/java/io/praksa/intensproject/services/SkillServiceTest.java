package io.praksa.intensproject.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import io.praksa.intensproject.models.Candidate;
import io.praksa.intensproject.models.Skill;
import io.praksa.intensproject.repositories.SkillRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SkillServiceTest {

	@Mock
	SkillRepository skillRepository;
	
	@InjectMocks
	SkillService skillService;
	
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    public Skill skillForTest() {
    	Skill skill = new Skill(1l, "Java");
    	return skill;
    } 
	
	@Test
	void findSkillByIdTest() {
		Skill skill = skillForTest();
		when(skillRepository.getOne(1l)).thenReturn(skill);
		assertEquals(skill.getId(), skillService.findSkillById(1l).getId());
		
		verify(skillRepository, times(1)).getOne(1l);
        verifyNoMoreInteractions(skillRepository);
	}
	
	@Test
	void findAllTest() {
		List<Skill> lista = new ArrayList<>();
		Skill skill = skillForTest();
		lista.add(skill);
		when(skillRepository.findAll()).thenReturn(lista);
		List<Skill> actualList = skillService.getAllSkills();
		assertThat(actualList).hasSize(1);
		
		verify(skillRepository, times(1)).findAll();
        verifyNoMoreInteractions(skillRepository);
	}
	
	@Test
	void addSkillTest() {
		List<Skill> lista = new ArrayList<>();
		Skill skill = skillForTest();
		Skill skill2 = new Skill();
		skill2.setId(2l);
		skill2.setName("c#");
		lista.add(skill2);
		when(skillRepository.findAll()).thenReturn(lista);
		when(skillRepository.save(skill)).thenReturn(skill);
		
		int sizeBeforeAdd = skillService.getAllSkills().size();
		
		Skill actualSkill = skillService.addSkill(skill);
		assertThat(actualSkill.getId()).isNotNull();
		
		when(skillRepository.findAll()).thenReturn(Arrays.asList(skill2, skill));
		List<Skill> lista2 = skillService.getAllSkills();
		assertThat(lista2).hasSize(sizeBeforeAdd+1);
		
		verify(skillRepository, times(3)).findAll();
		verify(skillRepository, times(1)).save(skill);
	}

	@Test
	void removeSkillTest() {
		List<Skill> lista = new ArrayList<>();
		Skill skill = skillForTest();
		Skill skill2 = new Skill();
		skill2.setId(2l);
		skill2.setName("c#");
		lista.add(skill);
		lista.add(skill2);
		when(skillRepository.findAll()).thenReturn(lista);
		when(skillRepository.getOne(1l)).thenReturn(skill);
		
		int sizeBeforeAdd = skillService.getAllSkills().size();
		
		Skill actualSkill = skillService.removeSkill(skill.getId());
		assertThat(actualSkill.getId()).isNotNull();
		
		when(skillRepository.findAll()).thenReturn(Arrays.asList(skill2));
		List<Skill> lista2 = skillService.getAllSkills();
		assertThat(lista2).hasSize(sizeBeforeAdd-1);
		
		verify(skillRepository, times(2)).findAll();
		verify(skillRepository, times(1)).getOne(skill.getId());
		verify(skillRepository, times(1)).delete(skill);
	}
	
	@Test
	void updateSkillTest() {
		List<Skill> lista = new ArrayList<>();
		Skill skill = skillForTest();
		lista.add(skill);
		when(skillRepository.findAll()).thenReturn(lista);
		when(skillRepository.getOne(1l)).thenReturn(skill);
		Skill skillUpdate = skill;
		skillUpdate.setName("c++");
		when(skillRepository.save(skillUpdate)).thenReturn(skillUpdate);
		Skill actualSkill = skillService.updateSkill(skill.getId(), skillUpdate);
		assertThat(actualSkill.getId()).isNotNull();
		assertEquals("c++", actualSkill.getName());
		
		verify(skillRepository, times(1)).findAll();
		verify(skillRepository, times(1)).getOne(skill.getId());
		verify(skillRepository, times(1)).save(skillUpdate);
	}
	
}
