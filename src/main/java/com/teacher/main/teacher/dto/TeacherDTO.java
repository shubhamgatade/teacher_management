package com.teacher.main.teacher.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teacher.main.network.entity.Student;
import com.teacher.main.network.entity.Subject;
import com.teacher.main.teacher.interfaces.StudentInterface;
import com.teacher.main.teacher.interfaces.SubjectInterface;

import lombok.Data;

@Data
public class TeacherDTO implements Serializable {

	private static final long serialVersionUID = -5208946812958425255L;
	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	private String email;
	private String role;
	private String username;
	private String password;
	private Boolean status;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	private Date createdOn;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	private Date updatedOn;
	private List<Integer> studentIds;
	private List<Integer> subjectIds;
	private List<StudentInterface> students;
	private List<SubjectInterface> subjects;
}