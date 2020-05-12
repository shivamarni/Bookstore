package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {
	@NotNull(message = "first name required for registration...")
	@Pattern(regexp = "^[a-zA-Z]*", message = "Digits and Special characters are not allowed")
	@Size(min = 3,max = 25,message = "first name must be in length of 7-25 only")
	private String firstName;
	
	@NotNull(message = "last name required for registration...")
	@Pattern(regexp = "^[a-zA-Z]*", message = "Digits and Special characters are not allowed")
	@Size(min = 3,max = 25,message = "last name must be in length of 7-25 only")
	private String lastName;
	
	@NotNull(message = "password required for registration...")
	@Pattern(regexp = "?=.*[A-Z]", message = "password must contain atlast one uppercase letter")
	@Size(min = 7,max = 25,message = "password must be in length of 7-25 only")
	private String password;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Pattern(regexp = "^([+]\\d{2})?\\d{10}$",message = "Incorrect mobile format for registration")
	private Long mobileNumber;
	
	private String address;

}
