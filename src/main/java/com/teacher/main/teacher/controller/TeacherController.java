package com.teacher.main.teacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teacher.main.exception.BadRequestException;
import com.teacher.main.network.entity.Student;
import com.teacher.main.network.entity.Subject;
import com.teacher.main.teacher.dto.TeacherDTO;
import com.teacher.main.teacher.entity.Teacher;
import com.teacher.main.teacher.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@PostMapping("/create")
	public Teacher create(@RequestBody TeacherDTO teacherDTO) throws BadRequestException {
		Teacher teacher = teacherService.create(teacherDTO);
		return teacher;
	}

	@GetMapping("/get")
	public Teacher getById(@RequestParam("idNo") Integer idNo) throws BadRequestException {
		Teacher teacher = teacherService.getById(idNo);
		return teacher;
	}
	
	@GetMapping("/detail/get")
	public TeacherDTO getCompleteDetailsById(@RequestParam("id") Integer id) throws BadRequestException {
		TeacherDTO teacher = teacherService.getCompleteTeacherDetailsById(id);
		return teacher;
	}

	@GetMapping("/getAll")
	public List<Teacher> getAllTeacherDetails() throws BadRequestException {
		List<Teacher> allTeacherDetails = teacherService.getAllTeacherDetails();
		return allTeacherDetails;
	}

	@GetMapping("/getAllActive")
	public List<Teacher> getAllActiveTeacherDetails() throws BadRequestException {
		List<Teacher> allActiveTeacherDetails = teacherService.getAllActiveTeacherDetails();
		return allActiveTeacherDetails;
	}

	@DeleteMapping("/delete/{idNo}")
	public String deleteById(@PathVariable("idNo") Integer idNo) throws BadRequestException {
		String response = teacherService.deleteById(idNo);
		return response;
	}

	@PutMapping("/update/{idNo}")
	public Teacher updateById(@PathVariable("idNo") Integer idNo, @RequestBody Teacher teacher)
			throws BadRequestException {
		Teacher response = teacherService.updateById(idNo, teacher);
		return response;
	}

	@PostMapping("/create/student")
	public Student create(@RequestBody Student student) throws Exception {
		student = teacherService.create(student);
		return student;
	}

	@PostMapping("/create/subject")
	public Subject create(@RequestBody Subject subject) throws Exception {
		subject = teacherService.create(subject);
		return subject;
	}
}