package com.bridgelabz.bookstore.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
	private long phoneNum;
}
