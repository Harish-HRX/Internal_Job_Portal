package com.team1.internalJobPortal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.team1.internalJobPortal.entity.Jobseeker;
import com.team1.internalJobPortal.entity.Recruiter;
import com.team1.internalJobPortal.service.JobseekerService;
import com.team1.internalJobPortal.service.RecruiterService;


@Controller
@RequestMapping("/home")
public class HomeController {

	
    private RecruiterService recruiterService;
    private JobseekerService jobseekerService;
	
	@Autowired
	public HomeController(RecruiterService theRecruiterService,JobseekerService theJobseekerService) {
    	recruiterService = theRecruiterService;
		jobseekerService = theJobseekerService;
	}
	
	
	@GetMapping("/menu")
	public String home() {
		return "menu";
	}
	
	@GetMapping("/showRecruiterSignUp")
	public String showRecruiterSignUp(Model model) {                 
		Recruiter recruiter=new Recruiter();
		model.addAttribute("recruiter", recruiter);
		return "recruiter/signUp";
	}
	
	@PostMapping("/processRecruiterSignUp")
	public String processRecruiterSignUp(@Valid@ModelAttribute("recruiter") Recruiter recruiter,BindingResult br) {
		if(br.hasErrors()) {
			return "recruiter/signUp";
		}
		else {
			recruiterService.save(recruiter);
			return "recruiter/signIn"; 
		}
	}
	
	@GetMapping("/showRecruiterSignIn")
	public String showRecruiterSignIn(Model model) {
		Recruiter recruiter=new Recruiter();
		model.addAttribute("recruiter", recruiter);
		return "recruiter/signIn";
	}
	
	@PostMapping("/processRecruiterSignIn")
	public String processRecruiterSignIn(@Valid@ModelAttribute("recruiter") Recruiter recruiter,BindingResult br,Model model) {
		int recruiterId=recruiterService.isValidRecruiter(recruiter);
		
		if(recruiterId==0) {
			return "recruiter/signIn";
		}
		else {
			recruiter=recruiterService.findById(recruiterId);
			model.addAttribute("recruiter1",recruiter);
			return "recruiter/recruiterMenu"; 
		}
	}
	
	
	@GetMapping("/showJobseekerSignUp")
	public String showSignUp(Model model) {                 
		Jobseeker jobseeker=new Jobseeker();
		model.addAttribute("jobseeker", jobseeker);
		return "jobseeker/signUp";
	}
	
	@PostMapping("/processJobseekerSignUp")
	public String processSignUp(@Valid@ModelAttribute("jobseeker") Jobseeker jobseeker,BindingResult br) {
		if(br.hasErrors()) {
			return "jobseeker/signUp";
		}
		else {
			jobseekerService.save(jobseeker);
			return "jobseeker/signIn"; 
		}
	}
	
	@GetMapping("/showJobseekerSignIn")
	public String showViewVal(Model model) {
		Jobseeker jobseeker=new Jobseeker();
		model.addAttribute("jobseeker", jobseeker);
		return "jobseeker/signIn";
	}
	
	@PostMapping("/processJobseekerSignIn")
	public String processSignIn(@Valid@ModelAttribute("jobseeker") Jobseeker jobseeker,BindingResult br,Model model) {
		int jobseekerId=jobseekerService.isValidJobseeker(jobseeker);
		
		if(jobseekerId==0) {
			return "jobseeker/signIn";
		}
		else {
			jobseeker=jobseekerService.findById(jobseekerId);
			model.addAttribute("jobseeker1",jobseeker);
			return "jobseeker/jobseekerMenu"; 
		}
	}
}
