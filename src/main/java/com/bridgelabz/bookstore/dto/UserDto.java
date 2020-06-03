package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	@NotBlank(message = " Name required for registration...")
	@Pattern(regexp = "^[a-zA-Z]*", message = "Digits and Special characters are not allowed")
	@Size(min = 3, max = 25, message = " Name must be in length of 3-25 only")
	private String name;

	@NotBlank(message = "password required for registration...")
	@Size(min = 6, max = 25, message = "password must be in length of 6-25 only")
	private String password;

	@NotBlank(message = "email required for registration...")
	@Email
	private String email;
	
	//@NotBlank(message="mobile number required for regitration")
	//@Pattern(regexp = "/^[0-9()-]+$/",message = "invalid mobile number type")
	private Long phoneNumber;
}
