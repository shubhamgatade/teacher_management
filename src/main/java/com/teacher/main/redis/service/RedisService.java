package com.teacher.main.redis.service;

import java.util.List;

import com.teacher.main.network.entity.Student;
import com.teacher.main.network.entity.Subject;
import com.teacher.main.teacher.entity.Teacher;

public interface RedisService {

	Subject setSubjectByIdInCache(Integer subjectId, Subject subject);

	Subject getSubjectByIdFromCache(Integer subjectId);

	Boolean clearSubjectByIdFromCache(Integer subjectId);

	Teacher setTeacherByIdInCache(Integer teacherId, Teacher teacher);

	Teacher getTeacherByIdFromCache(Integer teacherId);

	Boolean clearTeacherByIdFromCache(Integer teacherId);

	Student setStudentByIdInCache(Integer studentId, Student student);

	Student getStudentByIdFromCache(Integer studentId);

	Boolean clearStudentByIdFromCache(Integer studentId);

	List<Teacher> setTeacherListInCache(List<Teacher> teachers);

	List<Teacher> getTeacherListFromCache();
}
