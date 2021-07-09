package com.team1.internalJobPortal.service;

import java.util.List;
import com.team1.internalJobPortal.entity.Job;

public interface JobService {
	
	public List<Job> findAll();
	
	public Job findById(int theId);
	
	public void save(Job theJob);
	
	public void deleteById(int theId);
	
	public int findRecruiterId(int thejobId);
}
