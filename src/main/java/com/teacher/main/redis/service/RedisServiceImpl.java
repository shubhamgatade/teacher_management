package com.teacher.main.redis.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.teacher.main.network.entity.Student;
import com.teacher.main.network.entity.Subject;
import com.teacher.main.teacher.entity.Teacher;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Override
	@CachePut(value = "teacherCache", key = "'cache_teacher")
	public List<Teacher> setTeacherListInCache(List<Teacher> teachers) {
		return teachers;
	}

	@Override
	@Cacheable(value = "teacherCache", key = "'cache_teacher'")
	public List<Teacher> getTeacherListFromCache() {
		return null;
	}
	
	@Override
	@CachePut(value = "subjectCache", key = "'cache_subjectById_'.concat(#subjectId)")
	public Subject setSubjectByIdInCache(Integer subjectId, Subject subject) {
		return subject;
	}

	@Override
	@Cacheable(value = "studentCache", key = "'cache_subjectById_'.concat(#subjectId)")
	public Subject getSubjectByIdFromCache(Integer subjectId) {
		return null;
	}

	@Override
	@CacheEvict(value = "subjectCache", key = "'cache_subjectById_'.concat(#subjectId)")
	public Boolean clearSubjectByIdFromCache(Integer subjectId) {
		return null;
	}
	
	@Override
	@CachePut(value = "teacherCache", key = "'cache_teacherById_'.concat(#teacherId)")
	public Teacher setTeacherByIdInCache(Integer teacherId, Teacher teacher) {
		return teacher;
	}

	@Override
	@Cacheable(value = "studentCache", key = "'cache_teacherById_'.concat(#teacherId)")
	public Teacher getTeacherByIdFromCache(Integer teacherId) {
		return null;
	}

	@Override
	@CacheEvict(value = "teacherCache", key = "'cache_teacherById_'.concat(#teacherId)")
	public Boolean clearTeacherByIdFromCache(Integer teacherId) {
		return null;
	}
	
	@Override
	@CachePut(value = "studentCache", key = "'cache_studentById_'.concat(#studentId)")
	public Student setStudentByIdInCache(Integer studentId, Student student) {
		return student;
	}

	@Override
	@Cacheable(value = "studentCache", key = "'cache_studentById_'.concat(#studentId)")
	public Student getStudentByIdFromCache(Integer studentId) {
		return null;
	}

	@Override
	@CacheEvict(value = "studentCache", key = "'cache_studentById_'.concat(#studentId)")
	public Boolean clearStudentByIdFromCache(Integer studentId) {
		return null;
	}
}
