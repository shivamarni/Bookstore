package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="seller")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sellerId;
	@Column(name = "seller_firstName", nullable = false)
	private String firstName;
	@Column(name = "seller_lastName", nullable = false)
	private String lastName;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "seller_email", nullable = false)
	private String email;
	@Column(name = "phoneNumber", nullable = false)
	private Long phoneNumber;
	@Column(name = "verified", nullable = false)
	private boolean isVerified;
	@Column(name = "country", nullable = false)
	private String country;
	@Column(name = "zipCode", nullable = false)
	private Long zipCode;
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "sellerId")
	private List<Book> sellerBooks;
	

		

	
}
