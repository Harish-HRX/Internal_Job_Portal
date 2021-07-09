package com.team1.internalJobPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team1.internalJobPortal.entity.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

}
