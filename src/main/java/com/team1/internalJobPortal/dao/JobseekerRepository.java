package com.team1.internalJobPortal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team1.internalJobPortal.entity.Jobseeker;

public interface JobseekerRepository extends JpaRepository<Jobseeker, Integer> {

}
