package com.team1.internalJobPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team1.internalJobPortal.entity.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {
	
}
