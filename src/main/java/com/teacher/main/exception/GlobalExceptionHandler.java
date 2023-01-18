package com.teacher.main.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private ExceptionMapping exceptionMapping;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleException(final Exception exception,
                                      final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        ExceptionStatus status = new ExceptionStatus(exception.getMessage());
        error.setStatus(status);
        logger.error(exception.toString());
        exception.printStackTrace();
        return error;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse handleBadRequestException(BadRequestException exception,
                                                final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        String errorMessage = exceptionMapping.get(exception.getCode(), exception.getSpaceHolder());
        ExceptionStatus status = new ExceptionStatus(errorMessage);
        error.setStatus(status);
        logger.error(exception.toString());
        exception.printStackTrace();
        return error;
    }
}