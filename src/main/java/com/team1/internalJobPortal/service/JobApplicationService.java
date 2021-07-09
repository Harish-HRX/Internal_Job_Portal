package com.team1.internalJobPortal.service;

import java.util.List;
import com.team1.internalJobPortal.entity.JobApplication;

public interface JobApplicationService {
	
	public List<JobApplication> findAll();
	
	public JobApplication findById(int theId);
	
	public void save(JobApplication theJobApplication1);
	
	public void deleteById(int theId);
}