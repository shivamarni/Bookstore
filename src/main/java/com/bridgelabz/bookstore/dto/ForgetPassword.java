package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotBlank;
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
public class ForgetPassword {
//	@NotBlank(message = "password required for registration...")
//	@Size(min = 6, max = 25, message = "password must be in length of 6-25 only")
	private String password;

}
