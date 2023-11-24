package com.javaguides.springboot.service;

import java.util.List;

import com.javaguides.springboot.entity.User;

public interface UserService {
	User createUser(User user);
	
	User getUserByID(Long id);
	
	List<User> getAllUsers();
	
	User updateUser(User user);
	
	void deleteUser(Long userId);
}
