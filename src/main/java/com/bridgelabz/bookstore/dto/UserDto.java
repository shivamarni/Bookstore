package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	@NotNull(message = " Name required for registration...")
	@Pattern(regexp = "^[a-zA-Z]*", message = "Digits and Special characters are not allowed")
	@Size(min = 3, max = 25, message = " Name must be in length of 7-25 only")
	private String name;

	@NotNull(message = "password required for registration...")
	@Size(min = 6, max = 25, message = "password must be in length of 7-25 only")
	private String password;

	@NotNull
	@Email
	private String email;

	@NotNull(message = "phone number required for registration...")
	private Long phoneNumber;
}
