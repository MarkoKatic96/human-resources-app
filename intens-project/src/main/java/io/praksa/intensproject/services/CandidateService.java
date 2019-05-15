package io.praksa.intensproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.praksa.intensproject.models.Candidate;
import io.praksa.intensproject.repositories.CandidateRepository;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	public List<Candidate> getAllCandidates(){
		return candidateRepository.findAll();
	}
	
	public Candidate findCandidateById(Long id) {
		return candidateRepository.getOne(id);
	}
	
	public Candidate addCandidate(Candidate candidate) {
		if(candidate != null) {
			List<Candidate> candidates = getAllCandidates();
			for (Candidate candidate2 : candidates) {
				if(candidate2.getEmail().equals(candidate.getEmail())) {
					return null; //vec postoji prijava sa istim emailom
				}
			}
			return candidateRepository.save(candidate);
		}else {
			return null;
		}
	}
	
	public Candidate removeCandidate(Long id) {
		Candidate candidate = findCandidateById(id);
		candidateRepository.delete(candidate);
		return candidate;
	}
	
	public Candidate updateCandidate(Long id, Candidate candidateUpdate) {
		Candidate candidate = findCandidateById(id);
		if(candidateUpdate != null && candidate != null) {
			List<Candidate> candidates = getAllCandidates();
			for (Candidate candidate2 : candidates) {
				if(candidate2.getEmail().equals(candidate.getEmail()) && candidate2.getId() != id) {
					return null; //vec postoji prijava sa istim emailom
				}
			}
			candidate.setFullName(candidateUpdate.getFullName());
			candidate.setDateOfBirth(candidateUpdate.getDateOfBirth());
			candidate.setTelNumber(candidateUpdate.getTelNumber());
			candidate.setEmail(candidateUpdate.getEmail());
			return candidateRepository.save(candidate);		
		}else{	
			return null;			
		}
	}
	
}
