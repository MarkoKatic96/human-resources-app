package io.praksa.intensproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.praksa.intensproject.models.Candidate;
import io.praksa.intensproject.models.Search;
import io.praksa.intensproject.models.Skill;
import io.praksa.intensproject.repositories.CandidateRepository;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private SkillService skillService;
	
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
	
	public Candidate addSkillToSet(Long skillId, Long candidateId) {
		Candidate candidate = findCandidateById(candidateId);
		if(candidate != null) {
			List<Skill> skills = new ArrayList<Skill>();
			if(candidate.getSkills()!=null) {
				skills.addAll(candidate.getSkills());
			}
			for (Skill skill : skills) {
				if(skill.getId() == skillId) {
					return null; //vec ima taj skill
				}
			}
			skills.add(skillService.findSkillById(skillId));
			candidate.setSkills(skills);
			return candidateRepository.save(candidate);
		}else {
			return null;
		}
	}
	
	public Candidate removeSkillFromSet(Long skillId, Long candidateId) {
		Candidate candidate = findCandidateById(candidateId);
		if(candidate != null) {
			List<Skill> skills = new ArrayList<Skill>();
			if(candidate.getSkills()!=null) {
				skills.addAll(candidate.getSkills());
			}
			for (Skill skill : skills) {
				if(skill.getId() == skillId) {
					skills.remove(skill);
					break;
				}
			}
			candidate.setSkills(skills);
			return candidateRepository.save(candidate);
		}else {
			return null;
		}
	}
	
	public List<Skill> notAquiredSkills(Long candidateId){
		Candidate candidate = findCandidateById(candidateId);
		if(candidate != null) {
			int ima = 0;
			List<Skill> returnList = new ArrayList<Skill>();
			List<Skill> allSkills = new ArrayList<Skill>();
			List<Skill> skills = new ArrayList<Skill>();	
			allSkills.addAll(skillService.getAllSkills());
			skills.addAll(candidate.getSkills());
			for (Skill skill : allSkills) {
				ima=0;
				for(Skill skill2 : skills) {
					if(skill2.getId() == skill.getId()) {
						ima = 1;
						break;//ima taj skill
					}
				}
				if(ima!=1) {
					returnList.add(skill);
				}
			}
			return returnList;
		}else {
			return null;
		}
	}
	
	public List<Candidate> search(Search search){
		List<Candidate> candidates = getAllCandidates();
		List<Candidate> filteredCandidates = new ArrayList<Candidate>();
		List<Candidate> returnCandidates = new ArrayList<Candidate>();
		returnCandidates.addAll(candidates);
		
		if(search.getFullName()!=null && !search.getFullName().isEmpty()) {
			for (Candidate candidate : returnCandidates) {
				if(candidate.getFullName().equals(search.getFullName())) {
					filteredCandidates.add(candidate);
				}
			}
			
			returnCandidates.clear();
			returnCandidates.addAll(filteredCandidates);
			filteredCandidates.clear();
			
		}
		
		if(search.getSkills()!=null) {
			for(int i = 0; i<search.getSkills().size(); i++) { //prolazak kroz sve skillove iz search
				for (Candidate candidate : returnCandidates) { //prolazak kroz sve kandidate
					for(int g = 0; g<candidate.getSkills().size(); g++) { //prolazak kroz sve skillove trenutnog kandidata
						if(search.getSkills().get(i) == ((List<Skill>) candidate.getSkills()).get(g).getId()) {
							filteredCandidates.add(candidate);
							break;
						}
					}
				}
				returnCandidates.clear();
				returnCandidates.addAll(filteredCandidates);
				filteredCandidates.clear();
			}
		}		
		return returnCandidates;
		
	}
	
}
