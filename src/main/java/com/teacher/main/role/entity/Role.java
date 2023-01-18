package com.teacher.main.role.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teacher.main.enums.Roles;
import com.teacher.main.utility.DateTimeHelper;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Enumerated(EnumType.STRING)
    private Roles name;
    private Integer level;
    private Boolean status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date createdOn;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date updatedOn;
    
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
