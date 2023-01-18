package com.teacher.main.common.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class TotalResponse implements Serializable {

    private static final long serialVersionUID = 2140149955859631998L;

    private Integer total;
    private Integer success;
    private Integer failure;
    private String name;
    private String errorMessage;
}