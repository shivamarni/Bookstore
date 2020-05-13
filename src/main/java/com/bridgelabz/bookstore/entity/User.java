package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Long phoneNumber;
	private String address;
	private String country;
	private Long zipcode; 
	@Value("false")
	private boolean isVerified;
	private LocalDateTime createdDate;
	@Value("null")
	private LocalDateTime updatedDate;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY)
	private List<Book> books;
}
