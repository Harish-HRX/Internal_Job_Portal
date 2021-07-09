package com.team1.internalJobPortal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.internalJobPortal.dao.JobseekerRepository;
import com.team1.internalJobPortal.entity.Job;
import com.team1.internalJobPortal.entity.JobApplication;
import com.team1.internalJobPortal.entity.Jobseeker;

@Service
public class JobseekerServiceImpl implements JobseekerService {

	private JobService jobService;
	private JobseekerRepository jobseekerRepository;
	private JobApplicationService jobApplicationService;
	
	@Autowired
	public JobseekerServiceImpl(JobseekerRepository theJobseekerRepository,JobApplicationService theJobApplicationService,JobService theJobService) {
		jobService=theJobService;
		jobseekerRepository = theJobseekerRepository;
		jobApplicationService = theJobApplicationService;
	}
	
	@Override
	public List<Jobseeker> findAll() {
		return jobseekerRepository.findAll();
	}
	
	@Override
	public Jobseeker findById(int theId) {
		Optional<Jobseeker> result = jobseekerRepository.findById(theId);
		
		Jobseeker theJobseeker = null;
		
		if (result.isPresent()) {
			theJobseeker = result.get();
		}
		else {
			// we didn't find the jobseeker
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theJobseeker;
	}

	@Override
	public void save(Jobseeker theJobseeker) {
		jobseekerRepository.save(theJobseeker);
	}

	@Override
	public void deleteById(int theId) {
		jobseekerRepository.deleteById(theId);
	}
	
	@Override
	public int isValidJobseeker(Jobseeker jobseeker1) {
		
		List<Jobseeker> jobseekers=jobseekerRepository.findAll();
		
		for(Jobseeker jobseeker2:jobseekers) {
			
			boolean mail=jobseeker2.getEmail().equals(jobseeker1.getEmail());
			boolean password=jobseeker2.getEmail().equals(jobseeker1.getEmail());
			if(mail&&password) {
					return jobseeker2.getJobseekerId();
			}
		
		}
	
		return 0;
	}
	

	@Override
	public List<Job> appliedJobs(int jobseekerId) {
		
		List<JobApplication> jobApplications=jobApplicationService.findAll();
		List<Job> appliedJobs=new ArrayList<>();
		
		for(JobApplication jobApplication:jobApplications) {
			if(jobApplication.getJobseekerId()==jobseekerId) {
				appliedJobs.add(jobService.findById(jobApplication.getJobId()));
			}
		}
		
		return appliedJobs;
		
	}
	
	@Override
	public List<JobApplication> appliedApplications(int jobseekerId) {
		
		List<JobApplication> jobApplications=jobApplicationService.findAll();
		List<JobApplication> appliedJobApplications=new ArrayList<>();
		
		for(JobApplication jobApplication:jobApplications) {
			if(jobApplication.getJobseekerId()==jobseekerId) {
				appliedJobApplications.add(jobApplication);
			}
		}
		
		return appliedJobApplications;
		
	}

	@Override
	public String[] statusConverter(List<JobApplication> jobApplications) {
		String [] status=new String[jobApplications.size()];
		int index=0;
		for(JobApplication jobApplication : jobApplications) {
			if(jobApplication.getStatus()==0) {
				status[index++]="Pending...";
			}
			if(jobApplication.getStatus()==1) {
				status[index++]="Selected";
			}
			if(jobApplication.getStatus()==2) {
				status[index++]="Rejected";
			}
		}
		return status;
	}

	@Override
	public List<Job> findUnappliedAll(int jobseekerId) {
		List<Job> unappliedJobs=jobService.findAll();
		Job job;
		for(JobApplication jobApplication:jobApplicationService.findAll()) {
			if(jobApplication.getJobseekerId()==jobseekerId) {
				job=jobService.findById(jobApplication.getJobId());
				unappliedJobs.remove(job);
			}
		}
		return unappliedJobs;
	}


}
