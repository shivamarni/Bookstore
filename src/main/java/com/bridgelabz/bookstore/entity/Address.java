package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	private String country;
	private String state;
	private String district;;
	private String city;
	private Long zipcode;
	private String street;
	private String addressType;
	private String doorNo;
	private LocalDateTime createdTime;
	@Value("null")
	private LocalDateTime updatedtime;
}
