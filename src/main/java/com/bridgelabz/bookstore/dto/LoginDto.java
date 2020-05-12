package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {	
	@NotNull
	@Email
	private String email;
	@NotNull(message = "password required for registration...")
	@Pattern(regexp = "?=.*[A-Z]", message = "password must contain atlast one uppercase letter")
	@Size(min = 7,max = 25,message = "password must be in length of 7-25 only")
	private String password;
}
