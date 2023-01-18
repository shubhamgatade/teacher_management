package com.teacher.main.user.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teacher.main.user.entity.User;
import com.teacher.main.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// Save method:
	@Override
	public Integer saveUser(User user) {

		// Encode Password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		int id = userRepository.save(user).getId();
		return id;
	}

	// get user by username:
	@Override
	public Optional<User> findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	// loadUserByUserName this method is used to get User from DB and
	// this is inBuilt method from UserDetailsService
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> opt = findByUsername(username);
		if (opt.isEmpty()) {
			throw new UsernameNotFoundException("User not exist");
		}

		// read user (from DB)
		User user = opt.get();

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
	}

}
