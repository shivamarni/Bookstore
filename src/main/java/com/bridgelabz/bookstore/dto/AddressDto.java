package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
	
	@NotNull(message = "country name is required")
	private String address;

	private String landmark;
	private String country;
	private String city;
	
	@NotNull(message="zipcode is required for delivering books")
	private Long pinCode;
	
	@NotNull(message="name is required for delivering books")
	private String name;
	
	private String addressType;
	
	@NotNull(message="doorNo is required for delivering books")
	private Long phonenumber;

}
