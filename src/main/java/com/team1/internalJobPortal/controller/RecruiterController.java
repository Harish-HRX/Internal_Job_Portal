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
import com.team1.internalJobPortal.entity.Recruiter;
import com.team1.internalJobPortal.service.JobApplicationService;
import com.team1.internalJobPortal.service.JobService;
import com.team1.internalJobPortal.service.RecruiterService;

@Controller
@SessionAttributes("recruiter")
@RequestMapping("/recruiter")
public class RecruiterController {

	private JobService jobService;
	private RecruiterService recruiterService;
	private JobApplicationService jobApplicationService;
	

	public RecruiterController(JobService theJobService,RecruiterService theRecruiterService,JobApplicationService theJobApplicationService) {
		jobService = theJobService;
		recruiterService = theRecruiterService;
		jobApplicationService=theJobApplicationService;
	}
	
	@GetMapping("/recruiterMenu")
	public String showrecruiterMenu(@RequestParam("recruiterId") int recruiterId,Model model) {
		Recruiter recruiter=recruiterService.findById(recruiterId);
		model.addAttribute("recruiter1",recruiter);
		return "recruiter/recruiterMenu"; 
	}
	
	@GetMapping("/showPostJobs")
	public String showPostJob(@RequestParam("recruiterId") int therecruiterId,Model model) {
		Recruiter recruiter=recruiterService.findById(therecruiterId);
		Job job=new Job();
		model.addAttribute("job", job);
		model.addAttribute("recruiter", recruiter);
		return "recruiter/postJobs";
	}
	
	@PostMapping("/processPostJobs")
	public String processPostJob(@Valid@ModelAttribute("job") Job job,BindingResult br,Model model) {
		if(br.hasErrors()) {
			return "recruiter/postJobs";
		}
		else {
			Recruiter recruiter=(Recruiter) model.getAttribute("recruiter");
			job.setRecruiterId(recruiter.getRecruiterId());
			jobService.save(job);
			model.addAttribute("recruiter1",recruiter);
			return "recruiter/recruiterMenu"; 
		}
	}
	
	@GetMapping("/showViewApplicants")
	public String showViewApplicants(@RequestParam("recruiterId") int therecruiterId,Model model) {
		Recruiter recruiter=recruiterService.findById(therecruiterId);
		model.addAttribute("recruiter", recruiter);
		
		List<Jobseeker> applicants=recruiterService.findPendingApplicantsbyRecruiterId(therecruiterId);
		model.addAttribute("applicants",applicants);
		return "recruiter/viewApplicants";
	}

	@GetMapping("/processSelectViewApplicants")
	public String processSelectViewApplicants(@RequestParam("index") String index,Model model) {
		int jobseekerIndex=Integer.parseInt(index);
		Recruiter recruiter=(Recruiter) model.getAttribute("recruiter");
		List<JobApplication> applications=recruiterService.findPendingApplicationsbyRecruiterId(recruiter.getRecruiterId());
		
		
		JobApplication jobApplication=applications.get(jobseekerIndex);
		jobApplication.setStatus(1);
		jobApplicationService.save(jobApplication);
		
		List<Jobseeker> applicants=recruiterService.findPendingApplicantsbyRecruiterId(recruiter.getRecruiterId());
		model.addAttribute("applicants",applicants);
		return "recruiter/viewApplicants";
	}
	
	@GetMapping("/processRejectViewApplicants")
	public String processRejectViewApplicants(@RequestParam("index") String index,Model model) {
		int jobseekerIndex=Integer.parseInt(index);
		Recruiter recruiter=(Recruiter) model.getAttribute("recruiter");
		List<JobApplication> applications=recruiterService.findPendingApplicationsbyRecruiterId(recruiter.getRecruiterId());
		
		JobApplication jobApplication=applications.get(jobseekerIndex);
		jobApplication.setStatus(2);
		jobApplicationService.save(jobApplication);
		
		List<Jobseeker> applicants=recruiterService.findPendingApplicantsbyRecruiterId(recruiter.getRecruiterId());
		model.addAttribute("applicants",applicants);
		return "recruiter/viewApplicants";
	}
	
		@GetMapping("/showSelectedApplicants")
		public String showSelectedApplicants(@RequestParam("recruiterId") int therecruiterId,Model model) {
			Recruiter recruiter=recruiterService.findById(therecruiterId);
			model.addAttribute("recruiter", recruiter);
			
			List<Jobseeker> applicants=recruiterService.findSelectedApplicantsbyRecruiterId(therecruiterId);
			model.addAttribute("applicants",applicants);	
			return "recruiter/viewSelectedApplicants";
		}

		@GetMapping("/processSelectedApplicants")
		public String processSelectApplicants(@RequestParam("index") String index,Model model) {
			int jobseekerIndex=Integer.parseInt(index);
			Recruiter recruiter=(Recruiter) model.getAttribute("recruiter");
			List<JobApplication> applications=recruiterService.findPendingApplicationsbyRecruiterId(recruiter.getRecruiterId());
			
			JobApplication jobApplication=applications.get(jobseekerIndex);
			jobApplication.setStatus(1);
			
			List<Jobseeker> applicants=recruiterService.findPendingApplicantsbyRecruiterId(recruiter.getRecruiterId());
			model.addAttribute("applicants",applicants);
			return "recruiter/viewSelectedApplicants";
		}
		

		@GetMapping("/showUpdateprofile")
		public String showUpdateprofile(@RequestParam("recruiterId") int therecruiterId,Model model) {
			Recruiter recruiter=recruiterService.findById(therecruiterId);
			model.addAttribute("recruiter", recruiter);
			return "recruiter/update";
		}

		@PostMapping("/processUpdateprofile")
		public String processUpdateprofile(@Valid@ModelAttribute("recruiter") Recruiter recruiter,BindingResult br) {
			if(br.hasErrors()) {
				return "recruiter/update"; 
			}
			else {
				recruiterService.save(recruiter);
				return "recruiter/update"; 
			}
		}
			
		@GetMapping("/showPostedJob")
		public String showPostedJob(@RequestParam("recruiterId") int therecruiterId,Model model) {
			Recruiter recruiter=recruiterService.findById(therecruiterId);
			model.addAttribute("jobs",recruiterService.findJobspostedbyRecruiterId(therecruiterId));
			model.addAttribute("recruiter", recruiter);
			return "recruiter/jobsPosted";
		}
			
		@PostMapping("/processPostedJob")
		public String processPostedJob(@Valid@ModelAttribute("job") Job job,BindingResult br,Model model) {
			if(br.hasErrors()) {
				return "recruiter/postJobs";
			}
			else {
				Recruiter recruiter=(Recruiter) model.getAttribute("recruiter");
				job.setRecruiterId(recruiter.getRecruiterId());
				jobService.save(job);
				model.addAttribute("recruiter1",recruiter);
				return "recruiter/recruiterMenu"; 
			}
		}
}
