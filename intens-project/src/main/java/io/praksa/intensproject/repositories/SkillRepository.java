package io.praksa.intensproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.praksa.intensproject.models.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

}
