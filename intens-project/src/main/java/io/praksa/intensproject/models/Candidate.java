package io.praksa.intensproject.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String fullName;
	
	private Date dateOfBirth;
	
	@NotNull
	private String telNumber;
	
	@NotNull
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
        name = "candidate_skills", 
        joinColumns = @JoinColumn(
          name = "candidate_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "skill_id", referencedColumnName = "id")) 
    private Collection<Skill> skills;

	public Candidate() {

	}

	public Candidate(Long id, @NotNull String fullName, @NotNull Date dateOfBirth, @NotNull String telNumber,
			@NotNull String email, Collection<Skill> skills) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.telNumber = telNumber;
		this.email = email;
		this.skills = skills;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Collection<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Collection<Skill> skills) {
		this.skills = skills;
	}
	
}
