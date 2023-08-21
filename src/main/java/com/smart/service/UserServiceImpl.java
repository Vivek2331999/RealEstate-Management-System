package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public User createUser(User user) {

		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setEmail("Email");

		return userRepository.save(user);
	}

	@Override
	public boolean checkEmail(String email) {

		return userRepository.existsByEmail(email);
	}

	

}
