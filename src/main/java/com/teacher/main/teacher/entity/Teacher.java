package com.teacher.main.teacher.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teacher.main.role.entity.Role;
import com.teacher.main.utility.DateTimeHelper;

import lombok.Data;

@Data
@Entity
@Table(name = "teacher_details")
public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "teacher_roles", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@PrePersist
	public void prePersist() {

		createdOn = DateTimeHelper.getCurrentDateTime();
		status = true;
	}

	@PreUpdate
	public void postUpdate() {

		updatedOn = DateTimeHelper.getCurrentDateTime();
		status = status == null || status;
	}
}