package com.teacher.main.role.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacher.main.role.entity.Role;
import com.teacher.main.role.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role create(Role role) {

		if (Objects.nonNull(role)) {
			return roleRepository.save(role);
		}
		return null;
	}
}