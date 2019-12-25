package com.gp.learners.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.gp.learners.entities.CourseFee;
import com.gp.learners.entities.StudentPackage;


public interface CourseFeeRepository extends JpaRepository<CourseFee, Integer>{
	
	@Query(value="select course_fee_id,amount,date,method,student_package_id from course_fee u WHERE u.student_package_id = :studentPackageId ",nativeQuery=true)
	public List<Object> findByStudentPackageId(@PathVariable("studentPackageId")StudentPackage studentPackageId);
	
	@Query(value="select sum(amount) from course_fee u WHERE u.student_package_id = :studentPackageId",nativeQuery=true)
	public Float getTotalFee(@PathVariable("studentPackageId")StudentPackage studentPackageId );
	
	//@Query(value="select * from course_fee where student_package_id = :studentPackageId",nativeQuery=true)
	//public List<CourseFee> getCourseFeeListByStudentPackageId(@Param("studentPackageId") Integer studentPackageId);
	
	public void deleteByStudentPackageId(StudentPackage studentPackageId);
}