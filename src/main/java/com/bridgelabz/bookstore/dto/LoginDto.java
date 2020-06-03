package com.bridgelabz.bookstore.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {	
	@NotBlank(message = "email required for login")
	@Email
	private String email;
	@NotBlank(message = "password required for login")
	@Size(min = 6,max = 25)
	private String password;
}
