package com.teacher.main.teacher.service;

import java.util.List;

import com.teacher.main.exception.BadRequestException;
import com.teacher.main.network.entity.Student;
import com.teacher.main.network.entity.Subject;
import com.teacher.main.teacher.dto.TeacherDTO;
import com.teacher.main.teacher.entity.Teacher;

public interface TeacherService {

	Teacher create(TeacherDTO teacherDTO) throws BadRequestException;

	Teacher getById(Integer id) throws BadRequestException;

	String deleteById(Integer id) throws BadRequestException;

	Teacher updateById(Integer id, Teacher teacher) throws BadRequestException;

	Subject create(Subject subject) throws Exception;

	Student create(Student student) throws Exception;

	List<Teacher> getAllTeacherDetails() throws BadRequestException;

	List<Teacher> getAllActiveTeacherDetails() throws BadRequestException;

	TeacherDTO getCompleteTeacherDetailsById(Integer id) throws BadRequestException;
}
