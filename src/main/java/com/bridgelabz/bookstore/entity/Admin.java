package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;
	@Column(name = "admin_firstName", nullable = false)
	private String firstName;
	@Column(name = "admin_lastName", nullable = false)
	private String lastName;
	@Column(name = "admin_email", nullable = false)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "phoneNumber", nullable = false)
	private Long phoneNumber;
	@Column(name = "created_time", nullable = false)
	private LocalDateTime createdTime;
	
	private LocalDateTime updatedTime;
	@Column(name = "verified", nullable = false)
	private boolean verified;

}
