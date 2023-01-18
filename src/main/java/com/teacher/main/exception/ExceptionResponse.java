package com.teacher.main.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = -8501222995668304157L;
    private ExceptionStatus status;
}
