package com.teacher.main.teacher.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface StudentInterface {

//	@JsonIgnore
	Integer getId();
	String getName();
	Integer getAge();
	String getGender();
	String getEmail();
	Double getAttendance();
	Boolean getStatus();
}
