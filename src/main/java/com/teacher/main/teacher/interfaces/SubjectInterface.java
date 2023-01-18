package com.teacher.main.teacher.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface SubjectInterface {

//	@JsonIgnore
	Integer getId();
	String getName();
	String getType();
	Double getDuration();
	Boolean getStatus();
}
