package com.team1.internalJobPortal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_application")
public class JobApplication {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="application_id")
	private int applicationId;

	@Column(name="job_id")
	private int jobId;
	
	@Column(name="jobseeker_id")
	private int jobseekerId;
	
	@Column(name="recruiter_id")
	private int recruiterId;
	
	@Column(name="status")
	private int status;

	public JobApplication() {
		super();
	}
	
	public JobApplication(int jobId, int jobseekerId, int recruiterId) {
		super();
		this.jobId = jobId;
		this.jobseekerId = jobseekerId;
		this.recruiterId = recruiterId;
	}

	public JobApplication(int jobId, int jobseekerId, int recruiterId, int status) {
		super();
		this.jobId = jobId;
		this.jobseekerId = jobseekerId;
		this.recruiterId = recruiterId;
		this.status = status;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getJobseekerId() {
		return jobseekerId;
	}

	public void setJobseekerId(int jobseekerId) {
		this.jobseekerId = jobseekerId;
	}

	public int getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(int recruiterId) {
		this.recruiterId = recruiterId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "JobApplication [applicationId=" + applicationId + ", jobId=" + jobId + ", jobseekerId=" + jobseekerId
				+ ", recruiterId=" + recruiterId + ", status=" + status + "]";
	}

}
