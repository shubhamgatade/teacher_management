package com.teacher.main.user.service;

import java.util.Optional;

import com.teacher.main.user.entity.User;

public interface UserService {

	Integer saveUser(User u);

	Optional<User> findByUsername(String username);
}
