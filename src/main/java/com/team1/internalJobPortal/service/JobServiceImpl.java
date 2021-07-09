package com.team1.internalJobPortal.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.internalJobPortal.dao.JobRepository;
import com.team1.internalJobPortal.entity.Job;

@Service
public class JobServiceImpl implements JobService {

	private JobRepository jobRepository;
	
	@Autowired
	public JobServiceImpl(JobRepository theJobRepository) {
		jobRepository = theJobRepository;
	}
	
	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}
	
	@Override
	public Job findById(int theId) {
		Optional<Job> result = jobRepository.findById(theId);
		
		Job theJob = null;
		
		if (result.isPresent()) {
			theJob = result.get();
		}
		else {
			throw new RuntimeException("Did not find job id - " + theId);
		}
		
		return theJob;
	}

	@Override
	public void save(Job theJob) {
		jobRepository.save(theJob);
	}

	@Override
	public void deleteById(int theId) {
		jobRepository.deleteById(theId);
	}

	@Override
	public int findRecruiterId(int thejobId) {
		for(Job job:jobRepository.findAll()) {
			if(job.getJobId()==thejobId) {
				return job.getRecruiterId();
			}
		}
		return 0;
	}

}
