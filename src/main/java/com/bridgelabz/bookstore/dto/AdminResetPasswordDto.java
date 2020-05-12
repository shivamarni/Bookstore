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
public class AdminResetPasswordDto {
	@NotNull
	@Email
	private String email;
	private String token;

}
