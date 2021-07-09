package com.team1.internalJobPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team1.internalJobPortal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
