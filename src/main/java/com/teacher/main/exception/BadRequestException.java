package com.teacher.main.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ToString
public class BadRequestException extends Exception {

    private static final long serialVersionUID = 1L;

    Logger logger = LoggerFactory.getLogger(BadRequestException.class);

    private String code;

    private Map<String, String> spaceHolder = new HashMap<>();

    public BadRequestException(String code) {

        this.code = code;
    }

    public BadRequestException(String loggerError, String code) {

        logger.error(loggerError);
        this.code = code;
    }

    public BadRequestException setParam(String key, String value) {

        spaceHolder.put(key, value);
        return this;
    }
}

