package io.praksa.intensproject.models;

import java.util.List;

public class Search {

	private String fullName;
	
	private List<Skill> skills;

	public Search() {

	}
	
	public Search(String fullName, List<Skill> skills) {
		super();
		this.fullName = fullName;
		this.skills = skills;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
}
