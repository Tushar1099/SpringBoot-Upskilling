package com.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

	private long id;
	
	@Schema(
            description = "User First Name"
    )
//	User first name should not be empty
	@NotEmpty(message = "User first name should not be empty")
	private String firstName;
	
	@Schema(
            description = "User Last Name"
    )
//	User last name should not be empty
	@NotEmpty(message = "User last name should not be empty")
	private String lastName;
	
	@Schema(
            description = "User Email Address"
    )
//	User email should not be empty
//	Email address should be valid
	@NotEmpty(message="User email should not be empty")
	@Email(message="Email address should be valid")
	private String email;
}
