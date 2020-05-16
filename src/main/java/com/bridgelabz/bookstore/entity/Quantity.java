package com.bridgelabz.bookstore.entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;



import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quantity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quantityId;
	
	private Long cartQuantity;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "bookId", nullable = false)
	@JsonIgnore
	private Book book;

}
