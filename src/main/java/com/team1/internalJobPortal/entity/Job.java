package com.team1.internalJobPortal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="job")
public class Job {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="job_id")
	private int jobId;
	
	@Column(name="recruiter_id")
	private int recruiterId;
	
	@NotNull(message="is required")
	@Size(min=1,message="Title should contain min 2 character is required")
	@Column(name="title")
	private String title;
	
	@NotNull(message="is required")
	@Size(min=1,message="Salary should contain min 2 character is required")
	@Column(name="salary")
	private String salary;
	
	@NotNull(message="is required")
	@Size(min=1,message="Skills should contain min 2 character is required")
	@Column(name="skills_required")
	private String skillsRequired;
	
	@NotNull(message="is required")
	@Size(min=1,message="Experience should contain min 1 character is required")
	@Column(name="experience_required")
	private String experienceRequired;
	
	@NotNull(message="is required")
	@Size(min=1,message="Location should contain min 2 character is required")
	@Column(name="location")
	private String location;
	
	@NotNull(message="is required")
	@Size(min=1,message="Employment should contain min 2 character is required")
	@Column(name="employment")
	private String employment;
	
	@NotNull(message="is required")
	@Size(min=1,message="Description should contain min 2 character is required")
	@Column(name="description")
	private String description;

	public Job() {
		super();
	}

	public Job(int recruiterId, String title, String salary, String skillsRequired, String experienceRequired,
			String location, String employment, String description) {
		super();
		this.recruiterId = recruiterId;
		this.title = title;
		this.salary = salary;
		this.skillsRequired = skillsRequired;
		this.experienceRequired = experienceRequired;
		this.location = location;
		this.employment = employment;
		this.description = description;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(int id) {
		this.recruiterId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getSkillsRequired() {
		return skillsRequired;
	}

	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public String getExperienceRequired() {
		return experienceRequired;
	}

	public void setExperienceRequired(String experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", recruiterId=" + recruiterId + ", title=" + title + ", salary=" + salary
				+ ", skillsRequired=" + skillsRequired + ", experienceRequired=" + experienceRequired + ", location="
				+ location + ", employment=" + employment + ", description=" + description + "]";
	}
	
}
