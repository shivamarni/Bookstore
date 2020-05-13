package com.bridgelabz.bookstore.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {	
	@NotNull(message = "email required for login")
	@Email
	private String email;
	@NotNull(message = "password required for login")
	private String password;
}
