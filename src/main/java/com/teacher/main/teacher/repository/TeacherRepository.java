package com.teacher.main.teacher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teacher.main.teacher.entity.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

	@Query(value = "select * from teachers", nativeQuery = true)
	List<Teacher> getAllTeacherDetails();

	@Query(value = "select * from teachers t where t.status = true", nativeQuery = true)
	List<Teacher> getAllActiveTeacherDetails();

	Teacher findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);	
}