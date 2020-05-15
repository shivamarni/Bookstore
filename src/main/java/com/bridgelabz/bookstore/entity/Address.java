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
	@Column(name = "state", nullable = false)
	private String state;
	@Column(name = "district", nullable = false)
	private String district;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "zipcode", nullable = false)
	private Long zipcode;
	@Column(name = "street", nullable = false)
	private String street;
	@Column(name = "addressType")
	private String addressType;
	@Column(name = "doorNo", nullable = false)
	private String doorNo;
	private LocalDateTime createdTime;
	@Value("null")
	private LocalDateTime updatedtime;
}
