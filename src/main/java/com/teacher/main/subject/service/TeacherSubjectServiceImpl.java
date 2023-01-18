package com.teacher.main.subject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacher.main.subject.entity.TeacherSubject;
import com.teacher.main.subject.repository.TeacherSubjectRepository;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService {

	@Autowired
	private TeacherSubjectRepository teacherSubjectRepository;

	@Override
	public void create(TeacherSubject teacherSubject) {
		teacherSubjectRepository.save(teacherSubject);
	}

}
