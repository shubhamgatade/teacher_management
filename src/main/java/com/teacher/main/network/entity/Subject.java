package com.teacher.main.network.entity;

import lombok.Data;

@Data
public class Subject {

	private Integer id;
	private String name;
	private String type;
	private Double duration;
	private Boolean status;
}
