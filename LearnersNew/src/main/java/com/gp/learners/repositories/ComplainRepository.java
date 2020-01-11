package com.gp.learners.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gp.learners.entities.Complain;

public interface ComplainRepository extends JpaRepository < Complain, Integer>{
	
	
	//get video by id
		@Query("from Complain where complainId = :complainId")
		public Complain getComplainById(@Param("complainId")Integer complainId);
		
} 