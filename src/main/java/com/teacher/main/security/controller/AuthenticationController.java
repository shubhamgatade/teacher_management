package com.teacher.main.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacher.main.security.config.JwtUtil;
import com.teacher.main.security.model.UserRequest;
import com.teacher.main.security.model.UserResponse;
import com.teacher.main.user.entity.User;
import com.teacher.main.user.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UserService service;
	
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	// 1.save user data in db:
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User u) {
		Integer id = service.saveUser(u);
		String body = "User '" + id + "' Saved";
		// System.out.println(u);

		// return new ResponseEntity<String>(body,HttpStatus.OK);
		return ResponseEntity.ok(body);
	}

	// 2.validate user and generate token(login)
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request) {
		// validate un/pw with DB
		// if it get validate then it create token otherwise it will triggers to
		// InvalidUserAuthEntryPoint:
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		String token = util.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token, "Congrats!!! Token generated successfully."));
	}

}
