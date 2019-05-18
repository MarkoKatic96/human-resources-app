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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	
	@RequestMapping("/skills")
	public ResponseEntity<List<Skill>> getAllSkills(){
		List<Skill> skillList = skillService.getAllSkills();
		return new ResponseEntity<List<Skill>>(skillList, HttpStatus.OK);
	}
	
	@RequestMapping("/skills/{id}")
	public ResponseEntity<Skill> findSkillById(@PathVariable("id") Long id){
		Skill skill = skillService.findSkillById(id);
		return (skill != null) ? new ResponseEntity<Skill>(skill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/add/skill", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Skill> addSkill(@RequestBody Skill skillAdd){
		Skill skill = skillService.addSkill(skillAdd);
		return (skill != null) ? new ResponseEntity<Skill>(skill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/delete/skill/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Skill> removeSkill(@PathVariable("id") Long id){
		Skill skill = skillService.removeSkill(id);
		return (skill != null) ? new ResponseEntity<Skill>(skill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/update/skill/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Skill> updateSkill(@PathVariable("id") Long id, @RequestBody Skill skillUpdate){
		Skill skill = skillService.updateSkill(id, skillUpdate);
		return (skill != null) ? new ResponseEntity<Skill>(skill, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
