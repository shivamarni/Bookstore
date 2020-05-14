package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddressDto {
	
	@NotNull(message = "country name is required")
	private String country;
	
	private String state;
	private String district;
	private String city;
	
	@NotNull(message="zipcode is required for delivering books")
	private Long zipcode;
	
	@NotNull(message="street is required for delivering books")
	private String street;
	
	private String addressType;
	
	@NotNull(message="doorNo is required for delivering books")
	private String doorNo;

}
