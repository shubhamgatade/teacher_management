package com.teacher.main.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacher.main.student.entity.TeacherStudent;
import com.teacher.main.student.repository.TeacherStudentRepository;

@Service
public class TeacherStudentServiceImpl implements TeacherStudentService {

	@Autowired
	private TeacherStudentRepository teacherStudentRepository;

	@Override
	public void create(TeacherStudent teacherStudent) {
		
		teacherStudentRepository.save(teacherStudent);
	}	
}