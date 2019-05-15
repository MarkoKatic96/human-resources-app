package io.praksa.intensproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.praksa.intensproject.models.Candidate;
import io.praksa.intensproject.models.Search;
import io.praksa.intensproject.services.CandidateService;

@RestController
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;
	
	@RequestMapping("/candidates")
	public ResponseEntity<List<Candidate>> getAllCandidates(){
		List<Candidate> candidateList = candidateService.getAllCandidates();
		return new ResponseEntity<List<Candidate>>(candidateList, HttpStatus.OK);
	}
	
	@RequestMapping("/candidates/{id}")
	public ResponseEntity<Candidate> findCandidateById(@PathVariable("id") Long id){
		Candidate candidate = candidateService.findCandidateById(id);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/add/candidate", method = RequestMethod.POST)
	public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidateAdd){
		Candidate candidate = candidateService.addCandidate(candidateAdd);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/delete/candidate/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Candidate> removeCandidate(@PathVariable("id") Long id){
		Candidate candidate = candidateService.removeCandidate(id);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/update/candidate/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") Long id, @RequestBody Candidate candidateUpdate){
		Candidate candidate = candidateService.updateCandidate(id, candidateUpdate);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/add/candidateSkill/{skillId}/{candidateId}", method = RequestMethod.PUT)
	public ResponseEntity<Candidate> addSkillToSet(@PathVariable("skillId") Long skillId, @PathVariable("candidateId") Long candidateId){
		Candidate candidate = candidateService.addSkillToSet(skillId, candidateId);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/delete/candidateSkill/{skillId}/{candidateId}", method = RequestMethod.PUT)
	public ResponseEntity<Candidate> removeSkillFromSet(@PathVariable("skillId") Long skillId, @PathVariable("candidateId") Long candidateId){
		Candidate candidate = candidateService.removeSkillFromSet(skillId, candidateId);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<Candidate>> searchCandidate(@RequestBody Search search){
		List<Candidate> candidateList = candidateService.search(search);
		return (candidateList != null) ? new ResponseEntity<List<Candidate>>(candidateList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
