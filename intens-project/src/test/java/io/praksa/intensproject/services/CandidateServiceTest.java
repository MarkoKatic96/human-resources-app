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
import io.praksa.intensproject.repositories.CandidateRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CandidateServiceTest {

	@Mock
	CandidateRepository candidateRepository;
	
	@Mock
	SkillService skillService;
	
	@InjectMocks
	CandidateService candidateService;
	
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	void test() {
		List<Candidate> lista = new ArrayList<>();
		when(candidateRepository.findAll()).thenReturn(lista);
		assertThat(lista).hasSize(1);
		
		verify(candidateRepository, times(1)).findAll();
        verifyNoMoreInteractions(candidateRepository);
	}
	
	@Test
	void addCandidateTest() {
		List<Candidate> lista = new ArrayList<>();
		Candidate c = new Candidate();
		c.setId(1l);
		c.setFullName("Marko Katic");
		c.setTelNumber("789654");
		c.setEmail("uuu");
		Candidate c2 = new Candidate();
		c2.setId(1l);
		c2.setFullName("Marko Katic");
		c2.setTelNumber("789654");
		c2.setEmail("ooo");
		lista.add(c2);
		when(candidateRepository.findAll()).thenReturn(lista);
		when(candidateRepository.save(c)).thenReturn(c);
		
		int dbSizeBeforeAdd = candidateService.getAllCandidates().size();
		
		Candidate candidate = candidateService.addCandidate(c);
		assertThat(candidate.getId()).isNotNull();
		
		when(candidateRepository.findAll()).thenReturn(Arrays.asList(new Candidate(), c));
		List<Candidate> lista2 = candidateService.getAllCandidates();
		assertThat(lista2).hasSize(dbSizeBeforeAdd+1);
		
		verify(candidateRepository, times(3)).findAll();
		verify(candidateRepository, times(1)).save(candidate);
	}

}
