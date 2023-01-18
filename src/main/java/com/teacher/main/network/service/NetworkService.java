package com.teacher.main.network.service;

import com.teacher.main.network.entity.Student;
import com.teacher.main.network.entity.Subject;

public interface NetworkService {

	Student createStudent(Student student) throws Exception;

	Subject createSubject(Subject subject) throws Exception;
}
