package com.teacher.main.common.controller;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teacher.main.common.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	private static final Logger log = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private EmailService emailService;

	@GetMapping(value = "/simple/{email}")
	public @ResponseBody ResponseEntity sendSimpleEmail(@PathVariable("email") String email) {

		try {
			emailService.sendSimpleEmail(email, "Welcome", "This is a welcome email for you!!");
		} catch (MailException mailException) {
			log.error("Error while sending out email..{}", mailException.getStackTrace());
			return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
	}

	@GetMapping(value = "/file/{email}")
	public @ResponseBody ResponseEntity sendEmailAttachment(@PathVariable("email") String email) {

		try {
			emailService.sendEmailWithAttachment(email, "File Email Confirmation", "Check attachment",
					"E:\\Attendance.txt");
		} catch (MessagingException | FileNotFoundException mailException) {
			log.error("Error while sending out email..{}", mailException.getStackTrace());
			return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Please check your inbox for confirmation", HttpStatus.OK);
	}
}
