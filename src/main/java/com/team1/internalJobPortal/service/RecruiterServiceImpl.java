package com.team1.internalJobPortal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team1.internalJobPortal.dao.RecruiterRepository;
import com.team1.internalJobPortal.entity.Job;
import com.team1.internalJobPortal.entity.JobApplication;
import com.team1.internalJobPortal.entity.Jobseeker;
import com.team1.internalJobPortal.entity.Recruiter;

@Service
public class RecruiterServiceImpl implements RecruiterService {

	private JobService jobService;
	private RecruiterRepository recruiterRepository;
	private JobseekerService jobseekerService;
	private JobApplicationService jobApplicationService;
	
	
	
	public RecruiterServiceImpl() {
	}

	@Autowired
	public RecruiterServiceImpl(RecruiterRepository theRecruiterRepository,JobService theJobService,JobApplicationService theJobApplicationService,JobseekerService theJobseekerService) {
		jobService=theJobService;
		jobseekerService=theJobseekerService;
		recruiterRepository = theRecruiterRepository;
		jobApplicationService=theJobApplicationService;
	}
	
	@Override
	public List<Recruiter> findAll() {
		return recruiterRepository.findAll();
	}
	
	@Override
	public Recruiter findById(int theId) {
		Optional<Recruiter> result = recruiterRepository.findById(theId);
		
		Recruiter theRecruiter = null;
		
		if (result.isPresent()) {
			theRecruiter = result.get();
		}
		else {
			// we didn't find the recruiter
			throw new RuntimeException("Did not find recruiter id - " + theId);
		}
		
		return theRecruiter;
	}

	@Override
	public void save(Recruiter theRecruiter) {
		recruiterRepository.save(theRecruiter);
	}

	@Override
	public void deleteById(int theId) {
		recruiterRepository.deleteById(theId);
	}
	
	@Override
	public int isValidRecruiter(Recruiter recruiter1) {
		
		List<Recruiter> recruiters=recruiterRepository.findAll();
		
		for(Recruiter recruiter2:recruiters) {
			
			boolean mail=recruiter2.getEmail().equals(recruiter1.getEmail());
			boolean password=recruiter2.getEmail().equals(recruiter1.getEmail());
			if(mail&&password) {
					return recruiter2.getRecruiterId();
			}
		
		}
	
		return 0;
	}
	
	@Override
	public List<Jobseeker> findApplicantsbyRecruiterId(int recruiterId) {
		List<Jobseeker> applicants=new ArrayList<Jobseeker>();

		for(JobApplication jobApplication:jobApplicationService.findAll()) {
			if(recruiterId==jobApplication.getRecruiterId()) {
				applicants.add(jobseekerService.findById(jobApplication.getJobseekerId()));
			}
		}
		return applicants;
	}

	@Override
	public List<Jobseeker> findPendingApplicantsbyRecruiterId(int recruiterId) {
		List<Jobseeker>	applicants=new ArrayList<>();
		
		for(JobApplication application:jobApplicationService.findAll()) {
			if(application.getRecruiterId()==recruiterId&&application.getStatus()==0) {
				applicants.add(jobseekerService.findById(application.getJobseekerId()));
			}
		}
		return applicants;
	}

	@Override
	public List<Jobseeker> findSelectedApplicantsbyRecruiterId(int recruiterId) {
		List<Jobseeker>	applicants=new ArrayList<>();
		
		for(JobApplication application:jobApplicationService.findAll()) {
			if(application.getRecruiterId()==recruiterId&&application.getStatus()==1) {
				applicants.add(jobseekerService.findById(application.getJobseekerId()));
			}
		}
		return applicants;
	}

	@Override
	public List<JobApplication> findPendingApplicationsbyRecruiterId(int therecruiterId) {
		List<JobApplication> applications=new ArrayList<JobApplication>();
		
		for(JobApplication jobApplication:jobApplicationService.findAll()) {
			if(therecruiterId==jobApplication.getRecruiterId()&&jobApplication.getStatus()==0) {
				applications.add(jobApplicationService.findById(jobApplication.getApplicationId()));
			}
		}
		return applications;
	}
	
	@Override
	public List<Job> findJobspostedbyRecruiterId(int therecruiterId) {
		List<Job> jobs=new ArrayList<Job>();
		
		for(Job job:jobService.findAll()) {
			if(therecruiterId==job.getRecruiterId()) {
				jobs.add(job);
			}
		}
		return jobs;
	}

}
