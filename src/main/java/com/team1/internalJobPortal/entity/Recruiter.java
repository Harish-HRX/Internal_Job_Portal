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
@Table(name="recruiter")
public class Recruiter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recruiter_id")
	private int recruiterId;
	
	@Column(name="name")
	private String name;
	
	@Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+",message="Invalid email address")
	@Column(name="email")
	private String email;
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,15}$",message="Password must contain atleast 1 digit, lowercase, uppercase and special character")
	@Column(name="password")
	private String password;
	
	@NotNull(message="is required")
	@Size(min=1,message="Job should contain min 2 character is required")
	@Column(name="job")
	private String job;
	
	@Pattern(regexp="(0/91)?[7-9][0-9]{9}",message="Phone must contain only 10-12 digits")
	@Column(name="phone")
	private String phone;

	public Recruiter() {
		super();
	}

	public Recruiter(String name, String email, String password, String job, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.job = job;
		this.phone = phone;
	}


	public int getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(int recruiterId) {
		this.recruiterId = recruiterId;
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Recruiter [recruiterId=" + recruiterId + ", name=" + name + ", email=" + email + ", password="
				+ password + ", job=" + job + ", phone=" + phone + "]";
	}
		
}
