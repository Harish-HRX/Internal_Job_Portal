package com.team1.internalJobPortal.service;

import java.util.List;

import com.team1.internalJobPortal.entity.Job;
import com.team1.internalJobPortal.entity.JobApplication;
import com.team1.internalJobPortal.entity.Jobseeker;
import com.team1.internalJobPortal.entity.Recruiter;

public interface RecruiterService {
	
	public List<Recruiter> findAll();
	
	public void deleteById(int theId);
	
	public Recruiter findById(int theId);
	
	public void save(Recruiter theRecruiter);
	
	public int isValidRecruiter(Recruiter recruiter1);
	
	public List<Jobseeker> findApplicantsbyRecruiterId(int recruiterId);
	
	public List<Jobseeker> findPendingApplicantsbyRecruiterId(int recruiterId); 
	
	public List<Jobseeker> findSelectedApplicantsbyRecruiterId(int recruiterId);

	public List<JobApplication> findPendingApplicationsbyRecruiterId(int therecruiterId);

	public List<Job> findJobspostedbyRecruiterId(int therecruiterId);
}
