package com.javaguides.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;
import com.javaguides.springboot.exception.EmailAlreadyExistsException;
import com.javaguides.springboot.exception.ResourceNotFoundException;
import com.javaguides.springboot.mapper.AutoUserMapper;
import com.javaguides.springboot.mapper.UserMapper;
import com.javaguides.springboot.repo.UserRepo;
import com.javaguides.springboot.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService{

	private UserRepo userRepo;
	
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		Optional<User> optionalUser = userRepo.findByEmail(userDto.getEmail());
		
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists for this user");
		}
		
//		Convert UserDto into user JPA Entity
//		User user = UserMapper.mapToUser(userDto);
		User user = modelMapper.map(userDto, User.class);
//		User user = AutoUserMapper.MAPPER.mapToUser(userDto);
		
		User savedUser = userRepo.save(user);
		
//		Convert User JPA Entity into UserDto
//		UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
//		UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
		
		return savedUserDto;
	}

	@Override
	public UserDto getUserByID(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("name", "id", userId)
		);
//		User user = optionalUser.get();
//		return UserMapper.mapToUserDto(user);
		return modelMapper.map(user, UserDto.class);
//		return AutoUserMapper.MAPPER.mapToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
//		1st way
//		return allUsers.stream().map(UserMapper::mapToUserDto)
//				.collect(Collectors.toList());
		
//		2nd way
//		List<UserDto> list = new ArrayList<>();
//		
//		for(User user:allUsers) {
//			list.add(UserMapper.mapToUserDto(user));
//		}
	
//		by modelMapper
		List<UserDto> list = new ArrayList<>();
		
		for(User user:allUsers) {
			list.add(modelMapper.map(user, UserDto.class));
		}
		return list;
		
//		by Model Struct
//		List<UserDto> list = new ArrayList<>();
//		
//		for(User user:allUsers) {
//			list.add(AutoUserMapper.MAPPER.mapToUserDto(user));
//		}
//		return list;
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepo.findById(user.getId()).orElseThrow(
				() -> new ResourceNotFoundException("user", "id", user.getId())
		);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepo.save(existingUser);
//		return UserMapper.mapToUserDto(updatedUser);
		return modelMapper.map(updatedUser, UserDto.class);
//		return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long userId) {
		
		User existingUser = userRepo.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("user", "id", userId)
		);
		 userRepo.deleteById(userId);
	}

}
