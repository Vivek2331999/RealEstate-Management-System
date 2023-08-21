package com.smart.service;

import com.smart.entities.User;

public interface UserService {

	public User createUser(User user);

	public boolean checkEmail(String email);

}
