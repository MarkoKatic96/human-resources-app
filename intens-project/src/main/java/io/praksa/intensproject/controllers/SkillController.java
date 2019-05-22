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

import io.praksa.intensproject.models.Skill;
import io.praksa.intensproject.services.SkillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="skillController")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	
	@ApiOperation( value = "Returns all skills.", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})	
	@RequestMapping("/skills")
	public ResponseEntity<List<Skill>> getAllSkills(){
		List<Skill> skillList = skillService.getAllSkills();
		return (!skillList.isEmpty()) ? new ResponseEntity<List<Skill>>(skillList, HttpStatus.OK) : new ResponseEntity<List<Skill>>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Returns skill by id.", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping("/skills/{id}")
	public ResponseEntity<Skill> findSkillById(@PathVariable("id") Long id){
		Skill skill = skillService.findSkillById(id);
		return (skill != null) ? new ResponseEntity<Skill>(skill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Creates a new skill.", httpMethod = "POST")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 400, message ="Bad Request")})
	@RequestMapping(value = "/add/skill", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Skill> addSkill(@RequestBody Skill skillAdd){
		Skill skill = skillService.addSkill(skillAdd);
		return (skill != null) ? new ResponseEntity<Skill>(skill, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Deletes a skill by id.", httpMethod = "DELETE")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping(value = "/delete/skill/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Skill> removeSkill(@PathVariable("id") Long id){
		Skill skill = skillService.removeSkill(id);
		return (skill != null) ? new ResponseEntity<Skill>(skill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation( value = "Updates a skill.", httpMethod = "PUT")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK"),
							 @ApiResponse( code = 404, message ="Not Found")})
	@RequestMapping(value = "/update/skill/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Skill> updateSkill(@PathVariable("id") Long id, @RequestBody Skill skillUpdate){
		Skill skill = skillService.updateSkill(id, skillUpdate);
		return (skill != null) ? new ResponseEntity<Skill>(skill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
