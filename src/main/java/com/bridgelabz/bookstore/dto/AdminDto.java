package com.bridgelabz.bookstore.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
	@NotNull(message = "first name required for registration...")
	@Pattern(regexp = "^[a-zA-Z]*", message = "Digits and Special characters are not allowed")
	@Size(min = 3,max = 25,message = "first name must be in length of 7-25 only")
	private String firstName;
	@NotNull(message = "last name required for registration...")
	@Pattern(regexp = "^[a-zA-Z]*", message = "Digits and Special characters are not allowed")
	@Size(min = 3,max = 25,message = "last name must be in length of 7-25 only")
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull(message = "password required for registration...")
	@Size(min = 6,max = 25,message = "password must be in length of 7-25 only")
	private String password;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
	@NotNull(message="phone number required for registration")
	private Long phoneNumber;
}
