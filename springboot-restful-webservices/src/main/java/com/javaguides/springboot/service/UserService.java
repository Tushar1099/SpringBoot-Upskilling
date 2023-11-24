package com.javaguides.springboot.service;

import java.util.List;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto getUserByID(Long id);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUser(UserDto user);
	
	void deleteUser(Long userId);
}
