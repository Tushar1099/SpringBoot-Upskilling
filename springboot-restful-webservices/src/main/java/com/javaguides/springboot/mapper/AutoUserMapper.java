package com.javaguides.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;

@Mapper
public interface AutoUserMapper {

	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
	
//	@Mapping(sourse = "id",target = "userId")
//	If field names are different in Entity and DTO use @Mapping.
	UserDto mapToUserDto(User user);
	
	User mapToUser(UserDto userDto);
}
