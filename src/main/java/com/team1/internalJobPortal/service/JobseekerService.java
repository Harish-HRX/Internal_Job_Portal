package com.team1.internalJobPortal.service;

import java.util.List;

import com.team1.internalJobPortal.entity.Job;
import com.team1.internalJobPortal.entity.JobApplication;
import com.team1.internalJobPortal.entity.Jobseeker;

public interface JobseekerService {
	
	public List<Jobseeker> findAll();
	
	public void deleteById(int theId);
	
	public Jobseeker findById(int theId);

	public void save(Jobseeker thejobseeker1);
    
	int isValidJobseeker(Jobseeker jobseeker1);

	public List<Job> appliedJobs(int jobseekerId);

	public List<Job> findUnappliedAll(int jobseekerId);
	
	public List<JobApplication> appliedApplications(int jobseekerId);

	public String[] statusConverter(List<JobApplication> jobsApplication);
}
