package com.team1.internalJobPortal.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.internalJobPortal.dao.JobApplicationRepository;
import com.team1.internalJobPortal.entity.JobApplication;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	private JobApplicationRepository jobApplicationRepository;
	
	@Autowired
	public JobApplicationServiceImpl(JobApplicationRepository theJobApplicationRepository) {
		jobApplicationRepository = theJobApplicationRepository;
	}
	
	@Override
	public List<JobApplication> findAll() {
		return jobApplicationRepository.findAll();
	}
	
	@Override
	public JobApplication findById(int theId) {
		Optional<JobApplication> result = jobApplicationRepository.findById(theId);
		
		JobApplication theJobApplication = null;
		
		if (result.isPresent()) {
			theJobApplication = result.get();
		}
		else {
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theJobApplication;
	}

	@Override
	public void save(JobApplication theJobApplication) {
		jobApplicationRepository.save(theJobApplication);
	}

	@Override
	public void deleteById(int theId) {
		jobApplicationRepository.deleteById(theId);
	}
}
