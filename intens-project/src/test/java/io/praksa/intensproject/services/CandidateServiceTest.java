package io.praksa.intensproject.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

import io.praksa.intensproject.models.Candidate;
import io.praksa.intensproject.models.Search;
import io.praksa.intensproject.models.Skill;
import io.praksa.intensproject.repositories.CandidateRepository;
import io.praksa.intensproject.repositories.SkillRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CandidateServiceTest {

	@Mock
	CandidateRepository candidateRepository;
	
	@Mock
	SkillRepository skillRepository;
	
	@Mock
	SkillService skillService;
	
	@InjectMocks
	CandidateService candidateService;
	
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    public Candidate candidateForTest() {
    	Candidate c = new Candidate();
		c.setId(1l);
		c.setFullName("Marko Katic");
		c.setTelNumber("069/4578945");
		c.setEmail("marko@mail.com");
		return c;
    }
    
	@Test
	void findCandidateByIdTest() {
		Candidate c = candidateForTest();
		when(candidateRepository.getOne(1l)).thenReturn(c);
		assertEquals(c.getId(), candidateService.findCandidateById(c.getId()).getId());
		
		verify(candidateRepository, times(1)).getOne(1l);
        verifyNoMoreInteractions(candidateRepository);
	}
	
	@Test
	void findAllTest() {
		List<Candidate> lista = new ArrayList<>();
		Candidate c = candidateForTest();
		lista.add(c);
		when(candidateRepository.findAll()).thenReturn(lista);
		List<Candidate> actualList = candidateService.getAllCandidates();
		assertThat(actualList).hasSize(1);
		
		verify(candidateRepository, times(1)).findAll();
        verifyNoMoreInteractions(candidateRepository);
	}
	
	@Test
	void addCandidateTest() {
		List<Candidate> lista = new ArrayList<>();
		Candidate c = candidateForTest();
		Candidate c2 = new Candidate();
		c2.setId(2l);
		c2.setFullName("Mirko Mirkovic");
		c2.setTelNumber("064/789654");
		c2.setEmail("mirko@mail.com");
		lista.add(c2);
		when(candidateRepository.findAll()).thenReturn(lista);
		when(candidateRepository.save(c)).thenReturn(c);
		
		int sizeBeforeAdd = candidateService.getAllCandidates().size();
		
		Candidate candidate = candidateService.addCandidate(c);
		assertThat(candidate.getId()).isNotNull();
		
		when(candidateRepository.findAll()).thenReturn(Arrays.asList(c2, c));
		List<Candidate> lista2 = candidateService.getAllCandidates();
		assertThat(lista2).hasSize(sizeBeforeAdd+1);
		
		verify(candidateRepository, times(3)).findAll();
		verify(candidateRepository, times(1)).save(candidate);
	}
	
	@Test
	void removeCandidateTest() {
		List<Candidate> lista = new ArrayList<>();
		Candidate c = candidateForTest();
		Candidate c2 = new Candidate();
		c2.setId(2l);
		c2.setFullName("Mirko Mirkovic");
		c2.setTelNumber("064/789654");
		c2.setEmail("mirko@mail.com");
		lista.add(c);
		lista.add(c2);
		when(candidateRepository.findAll()).thenReturn(lista);
		when(candidateRepository.getOne(1l)).thenReturn(c);
		
		int sizeBeforeAdd = candidateService.getAllCandidates().size();
		
		Candidate candidate = candidateService.removeCandidate(c.getId());
		assertThat(candidate.getId()).isNotNull();
		
		when(candidateRepository.findAll()).thenReturn(Arrays.asList(c2));
		List<Candidate> lista2 = candidateService.getAllCandidates();
		assertThat(lista2).hasSize(sizeBeforeAdd-1);
		
		verify(candidateRepository, times(2)).findAll();
		verify(candidateRepository, times(1)).getOne(c.getId());
		verify(candidateRepository, times(1)).delete(c);
	}
	
	@Test
	void updateCandidateTest() {
		List<Candidate> lista = new ArrayList<>();
		Candidate c = candidateForTest();
		lista.add(c);
		when(candidateRepository.findAll()).thenReturn(lista);
		when(candidateRepository.getOne(1l)).thenReturn(c);
		Candidate cUpdate = c;
		cUpdate.setFullName("Novo Ime");
		when(candidateRepository.save(cUpdate)).thenReturn(cUpdate);
		Candidate candidate = candidateService.updateCandidate(c.getId(), cUpdate);
		assertThat(candidate.getId()).isNotNull();
		assertEquals("Novo Ime", candidate.getFullName());
		
		verify(candidateRepository, times(1)).findAll();
		verify(candidateRepository, times(1)).getOne(c.getId());
		verify(candidateRepository, times(1)).save(cUpdate);
	}
	
	@Test
	void addSkillToSetTest() {
		Candidate c = candidateForTest();
		Skill skill = new Skill(1l, "Java");
		when(candidateRepository.getOne(1l)).thenReturn(c);
		when(skillRepository.getOne(1l)).thenReturn(skill);
		when(skillService.findSkillById(1l)).thenReturn(skill);
		when(candidateRepository.save(c)).thenReturn(c);
		
		Candidate actualCandidate = candidateService.addSkillToSet(skill.getId(), c.getId());
		assertThat(actualCandidate.getId()).isNotNull();
		assertThat(actualCandidate.getSkills()).isNotEmpty();
		
		verify(candidateRepository, times(1)).getOne(c.getId());
		verify(candidateRepository, times(1)).save(actualCandidate);
	}
	
	@Test
	void removeSkillToSetTest() {
		Candidate c = candidateForTest();
		Skill skill = new Skill(1l, "Java");
		c.setSkills(Arrays.asList(skill));
		when(candidateRepository.getOne(1l)).thenReturn(c);
		when(skillRepository.getOne(1l)).thenReturn(skill);
		when(skillService.findSkillById(1l)).thenReturn(skill);
		when(candidateRepository.save(c)).thenReturn(c);
		
		Candidate actualCandidate = candidateService.removeSkillFromSet(skill.getId(), c.getId());
		assertThat(actualCandidate.getId()).isNotNull();
		assertThat(actualCandidate.getSkills()).isEmpty();
		
		verify(candidateRepository, times(1)).getOne(c.getId());
		verify(candidateRepository, times(1)).save(actualCandidate);
	}
	
	@Test
	void notAquiredSkillsTest() {
		Candidate c = candidateForTest();
		List<Skill> skills = new ArrayList<>();//svi skillovi
		Skill skill = new Skill(1l, "Java");
		skills.add(skill);
		Skill skill2 = new Skill(2l, "c#");
		skills.add(skill2);
		c.setSkills(Arrays.asList(skill));
		
		when(candidateRepository.getOne(1l)).thenReturn(c);
		when(skillRepository.findAll()).thenReturn(skills);
		when(skillService.getAllSkills()).thenReturn(skills);
		
		List<Skill> actualSkills = candidateService.notAquiredSkills(c.getId());
		assertThat(actualSkills).isNotEmpty();
		assertThat(actualSkills).hasSize(1);
		
		verify(candidateRepository, times(1)).getOne(c.getId());
	}
	
	@Test
	void searchTest() {
		List<Candidate> lista = new ArrayList<>();
		Candidate c = candidateForTest();
		Candidate c2 = new Candidate();
		c2.setId(2l);
		c2.setFullName("Mirko Mirkovic");
		c2.setTelNumber("064/789654");
		c2.setEmail("mirko@mail.com");
		lista.add(c);
		lista.add(c2);

		when(candidateRepository.findAll()).thenReturn(lista);
		
		List<Candidate> actualSearch = candidateService.search(new Search("Marko Katic", null));
		assertThat(actualSearch).isNotEmpty();
		assertThat(actualSearch).hasSize(1);
		assertThat(actualSearch).contains(c);
		
		verify(candidateRepository, times(1)).findAll();
	}

}
