package com.team1.internalJobPortal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="job_seeker")
public class Jobseeker {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="jobseeker_id")
	private int jobseekerId;
	
	@NotNull(message="is required1")
	@Size(min=1,message="Name should contain min 2 character is required")
	@Column(name="name")
	private String name;
	
	@Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+",message="Invalid email address")
	@Column(name="email")
	private String email;
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,15}$",message="Password must contain atleast 1 digit, lowercase, uppercase and special character")
	@Column(name="password")
	private String password;
	
	@NotNull(message="is required2")
	@Size(min=1,message="Job should contain min 2 character is required")
	@Column(name="job")
	private String job;
	
	@NotNull(message="is required3")
	@Size(min=1,message="Experience should contain min 1 character is required")
	@Column(name="experience")
	private String experience;
	
	@NotNull(message="is required4")
	@Size(min=1,message="Skills should contain min 2 character is required")
	@Column(name="skills")
	private String skills;
	
	@Pattern(regexp="(0/91)?[7-9][0-9]{9}",message="Phone must contain only 10-12 digits")
	@Column(name="phone")
	private String phone;

	public Jobseeker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Jobseeker(String name, String email, String password, String job, String experience, String skills,
			String phone) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.job = job;
		this.experience = experience;
		this.skills = skills;
		this.phone = phone;
	}

	public int getJobseekerId() {
		return jobseekerId;
	}

	public void setJobseekerId(int jobseekerId) {
		this.jobseekerId = jobseekerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Jobseeker [jobseekerId=" + jobseekerId + ", name=" + name + ", email=" + email + ", password="
				+ password + ", job=" + job + ", experience=" + experience + ", skills=" + skills + ", phone=" + phone
				+ "]";
	}

}
