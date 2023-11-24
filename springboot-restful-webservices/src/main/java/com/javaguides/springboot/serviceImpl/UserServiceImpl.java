package com.javaguides.springboot.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;
import com.javaguides.springboot.mapper.UserMapper;
import com.javaguides.springboot.repo.UserRepo;
import com.javaguides.springboot.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService{

	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
//		Convert UserDto into user JPA Entity
		User user = UserMapper.mapToUser(userDto);
		
		User savedUser = userRepo.save(user);
		
//		Convert User JPA Entity into UserDto
		UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		
		return savedUserDto;
	}

	@Override
	public UserDto getUserByID(Long userId) {
		Optional<User> optionalUser = userRepo.findById(userId);
		User user = optionalUser.get();
		return UserMapper.mapToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
		return allUsers.stream().map(UserMapper::mapToUserDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepo.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepo.save(existingUser);
		return UserMapper.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long userId) {
		 userRepo.deleteById(userId);
	}

}
