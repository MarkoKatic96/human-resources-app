package io.praksa.intensproject.models;

import java.util.List;

public class Search {

	private String fullName;
	
	private List<Long> skills;

	public Search() {

	}
	
	public Search(String fullName, List<Long> skills) {
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

	public List<Long> getSkills() {
		return skills;
	}

	public void setSkills(List<Long> skills) {
		this.skills = skills;
	}
	
}
