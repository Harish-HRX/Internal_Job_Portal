package com.team1.internalJobPortal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team1.internalJobPortal.entity.Job;
import com.team1.internalJobPortal.entity.JobApplication;
import com.team1.internalJobPortal.entity.Jobseeker;
import com.team1.internalJobPortal.service.JobApplicationService;
import com.team1.internalJobPortal.service.JobService;
import com.team1.internalJobPortal.service.JobseekerService;

@Controller
@SessionAttributes("jobseeker")
@RequestMapping("/jobseeker")
public class JobseekerController {

	private JobApplicationService jobApplicationService;
	private JobseekerService jobseekerService;
	private JobService jobService;
	
	public JobseekerController(JobseekerService theJobseekerService,JobService theJobService,JobApplicationService theJobApplicationService) {
		jobseekerService = theJobseekerService;
		jobService = theJobService;
		jobApplicationService=theJobApplicationService;
	}

	@GetMapping("/jobseekerMenu")
	public String showrecruiterMenu(@RequestParam("jobseekerId") int jobseekerId,Model model) {
		Jobseeker jobseeker=jobseekerService.findById(jobseekerId);
		
		model.addAttribute("jobseeker1",jobseeker);
		return "jobseeker/jobseekerMenu"; 
	}
		
	
	@GetMapping("/showSearchJobs")
	public String showSearchJob(@RequestParam("jobseekerId") int thejobseekerId,Model model) {
		Jobseeker jobseeker=jobseekerService.findById(thejobseekerId);
		List<Job> jobs=jobseekerService.findUnappliedAll(thejobseekerId);
		
		model.addAttribute("jobseeker", jobseeker);
		model.addAttribute("jobs",jobs);	
		return "jobseeker/searchJobs";
	}
	
	@GetMapping("/processSearchJobs")
	public String processSearchJob(@RequestParam("thejobId") int jobId,Model model) {
		Jobseeker jobseeker=(Jobseeker) model.getAttribute("jobseeker");
		JobApplication jobApplication=new JobApplication(jobId, jobseeker.getJobseekerId(), jobService.findRecruiterId(jobId));
		jobApplicationService.save(jobApplication);
		List<Job> jobs=jobseekerService.findUnappliedAll(jobseeker.getJobseekerId());
		
		model.addAttribute("jobseeker", jobseeker);
		model.addAttribute("jobs",jobs);
		return "jobseeker/searchJobs"; 
	}
	
	@GetMapping("/showAppliedJobs")
	public String showappliedJobs(@RequestParam("jobseekerId") int thejobseekerId,Model model) {
		Jobseeker jobseeker=jobseekerService.findById(thejobseekerId);
		List<Job> jobs=jobseekerService.appliedJobs(thejobseekerId);
		
		model.addAttribute("jobseeker", jobseeker);
		model.addAttribute("jobs",jobs);	
		return "jobseeker/appliedJobs";
	}
	
	@GetMapping("/processAppliedJobs")
	public String processAppliedJobs(@RequestParam("thejobId") int jobId,Model model) {
		Jobseeker jobseeker=(Jobseeker) model.getAttribute("jobseeker");
		JobApplication jobApplication=new JobApplication(jobId,jobseeker.getJobseekerId(),jobService.findRecruiterId(jobId));
		jobApplicationService.save(jobApplication);
		
		model.addAttribute("jobseeker1",jobseeker);
		return "jobseeker/jobseekerMenu"; 
	}
	
	
	@GetMapping("/showStatus")
	public String showStatus(@RequestParam("jobseekerId") int thejobseekerId,Model model) {
		List<Job> jobs=jobseekerService.appliedJobs(thejobseekerId);
		List<JobApplication> jobsApplication=jobseekerService.appliedApplications(thejobseekerId);
		String[] status=jobseekerService.statusConverter(jobsApplication);
		
		model.addAttribute("jobs",jobs);
		model.addAttribute("status",status);
		return "jobseeker/applicationStatus";
	}
		
	@GetMapping("/processStatus")
	public String processStatus(@RequestParam("thejobId") int jobId,Model model) {		
		Jobseeker jobseeker=(Jobseeker) model.getAttribute("jobseeker");
		JobApplication jobApplication=new JobApplication(jobId,jobseeker.getJobseekerId(),jobService.findRecruiterId(jobId));
		jobApplicationService.save(jobApplication);
			
		model.addAttribute("jobseeker1",jobseeker);
		return "jobseeker/jobseekerMenu"; 
	}
		
	@GetMapping("/showUpdateprofile")
	public String showUpdateprofile(@RequestParam("jobseekerId") int thejobseekerId,Model model) {	
		Jobseeker jobseeker=jobseekerService.findById(thejobseekerId);
		model.addAttribute("jobseeker", jobseeker);  
		return "jobseeker/update";
	}

	@PostMapping("/processUpdateprofile")
	public String processUpdateprofile(@Valid@ModelAttribute("jobseeker") Jobseeker jobseeker,BindingResult br) {
		if(br.hasErrors()) {
			return "jobseeker/update"; 
		}
		else {
			jobseekerService.save(jobseeker);
			return "jobseeker/update"; 
		}
	}
		
}