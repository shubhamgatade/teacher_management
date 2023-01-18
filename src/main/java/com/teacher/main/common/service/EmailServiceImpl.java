package com.teacher.main.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(String username, String password) throws Exception {

//		String to = username;
//		MimeMessage messageBodyPart = javaMailSender.createMimeMessage();
//		messageBodyPart.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
//		messageBodyPart.setContent(htmlText, "text/html");
//		messageBodyPart.setSubject("Welcome onboard");
//		javaMailSender.send(messageBodyPart);
	}

	@Override
	public void sendSimpleEmail(String toAddress, String subject, String message) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(toAddress);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		javaMailSender.send(simpleMailMessage);
	}

	@Override
	public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment)
			throws MessagingException, FileNotFoundException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setTo(toAddress);
		messageHelper.setSubject(subject);
		messageHelper.setText(message);
		FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
		messageHelper.addAttachment("Attendance", file);
		javaMailSender.send(mimeMessage);
	}

	public void sendNewPassword(String username, String newPassword) throws Exception {

//		String to = username;
//		MimeMessage messageBodyPart = javaMailSender.createMimeMessage();
//		messageBodyPart.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
//		messageBodyPart.setContent(htmlText, "text/html");
//		messageBodyPart.setSubject("Reset Password");
//		javaMailSender.send(messageBodyPart);
	}
}
