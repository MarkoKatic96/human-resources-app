package io.praksa.intensproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.praksa.intensproject.models.Skill;
import io.praksa.intensproject.repositories.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	public List<Skill> getAllSkills() {
		return skillRepository.findAll();
	}
	
	public Skill findSkillById(Long id) {
		return skillRepository.getOne(id);
	}
	
	public Skill addSkill(Skill skill) {
		if(skill != null) {
			List<Skill> skills = getAllSkills();
			for (Skill skill2 : skills) {
				if(skill2.getName().equals(skill.getName())) {
					return null; //vec postoji skill sa istim imenom
				}
			}
			return skillRepository.save(skill);
		}else {
			return null;
		}
	}
	
	public Skill removeSkill(Long id) {
		Skill skill = findSkillById(id);
		skillRepository.delete(skill);
		return skill;
	}
	
	public Skill updateSkill(Long id, Skill skillUpdate) {
		Skill skill = findSkillById(id);
		if(skillUpdate != null && skill != null) {
			List<Skill> skills = getAllSkills();
			for (Skill skill2 : skills) {
				if(skill2.getName().equals(skill.getName()) && skill2.getId() != id) {
					return null; //vec postoji skill sa istim imenom
				}
			}
			skill.setName(skillUpdate.getName());
			return skillRepository.save(skill);
		}else {
			return null;
		}
	}
	
}
