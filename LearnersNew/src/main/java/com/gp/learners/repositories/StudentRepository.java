package com.gp.learners.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp.learners.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
