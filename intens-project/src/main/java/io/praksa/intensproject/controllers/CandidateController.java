package io.praksa.intensproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.praksa.intensproject.models.Candidate;
import io.praksa.intensproject.models.Search;
import io.praksa.intensproject.models.Skill;
import io.praksa.intensproject.services.CandidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="candidateController")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;
	
	@ApiOperation( value = "Returns all candidates.", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	@RequestMapping(value = "/candidates", method = RequestMethod.GET)
	public ResponseEntity<List<Candidate>> getAllCandidates(){
		List<Candidate> candidateList = candidateService.getAllCandidates();
		return (!candidateList.isEmpty()) ? new ResponseEntity<List<Candidate>>(candidateList, HttpStatus.OK) : new ResponseEntity<List<Candidate>>( HttpStatus.NOT_FOUND );
	}
	
	@ApiOperation( value = "Returns candidate by id.", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping("/candidates/{id}")
	public ResponseEntity<Candidate> findCandidateById(@PathVariable("id") Long id){
		Candidate candidate = candidateService.findCandidateById(id);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Creates a new candidate.", httpMethod = "POST")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 400, message ="Bad Request")})
	@RequestMapping(value = "/add/candidate", method = RequestMethod.POST)
	public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidateAdd){
		Candidate candidate = candidateService.addCandidate(candidateAdd);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation( value = "Deletes a candidate by id.", httpMethod = "DELETE")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping(value = "/delete/candidate/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Candidate> removeCandidate(@PathVariable("id") Long id){
		Candidate candidate = candidateService.removeCandidate(id);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Updates a candidate.", httpMethod = "PUT")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping(value = "/update/candidate/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") Long id, @RequestBody Candidate candidateUpdate){
		Candidate candidate = candidateService.updateCandidate(id, candidateUpdate);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Adds a skill to the candidate.", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping(value = "/add/candidateSkill/{skillId}/{candidateId}", method = RequestMethod.GET)
	public ResponseEntity<Candidate> addSkillToSet(@PathVariable("skillId") Long skillId, @PathVariable("candidateId") Long candidateId){
		Candidate candidate = candidateService.addSkillToSet(skillId, candidateId);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Removes a skill from the candidate.", httpMethod = "DELETE")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping(value = "/delete/candidateSkill/{skillId}/{candidateId}", method = RequestMethod.DELETE)
	public ResponseEntity<Candidate> removeSkillFromSet(@PathVariable("skillId") Long skillId, @PathVariable("candidateId") Long candidateId){
		Candidate candidate = candidateService.removeSkillFromSet(skillId, candidateId);
		return (candidate != null) ? new ResponseEntity<Candidate>(candidate, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Finds candidates that have specified name or skills.", httpMethod = "POST")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<Candidate>> searchCandidate(@RequestBody Search search){
		List<Candidate> candidateList = candidateService.search(search);
		return (candidateList != null) ? new ResponseEntity<List<Candidate>>(candidateList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Finds all skills that candidate with id does not have.", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping(value = "/notAquiredSkills/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Skill>> notAquiredSkills(@PathVariable("id") Long candidateId){
		List<Skill> skills = candidateService.notAquiredSkills(candidateId);
		return (skills != null) ? new ResponseEntity<List<Skill>>(skills, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
