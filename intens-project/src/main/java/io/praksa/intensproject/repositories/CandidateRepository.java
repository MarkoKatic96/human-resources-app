package io.praksa.intensproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.praksa.intensproject.models.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{

}
