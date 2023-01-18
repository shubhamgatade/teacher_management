package com.teacher.main.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class ExceptionStatus implements Serializable {

    private static final long serialVersionUID = -1329235769941829979L;
    private Boolean success;
    private String message;

    ExceptionStatus() {
        success = false;
    }

    ExceptionStatus(String message) {
        success = false;
        this.message = message;
    }
}
