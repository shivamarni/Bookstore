package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	@Column(name = "country", nullable = false)
	private String country;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "landmark", nullable = false)
	private String landmark;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "pinCode", nullable = false)
	private Long pinCode;
	
	@Column(name = "addressType")
	private String addressType;
	@Column(name = "phonenumber", nullable = false)
	private Long phonenumber;
	private LocalDateTime createdTime;
	@Value("null")
	private LocalDateTime updatedtime;
}
