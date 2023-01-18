package com.teacher.main.common.service;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmail(String username, String password) throws Exception;

    void sendNewPassword(String username, String newPassword) throws Exception;

	void sendSimpleEmail(String toAddress, String subject, String message);

	void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment)
			throws MessagingException, FileNotFoundException;
}