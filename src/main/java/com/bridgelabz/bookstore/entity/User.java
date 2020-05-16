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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(name = "user_firstName", nullable = false)
	private String firstName;
	@Column(name = "user_lastName", nullable = false)
	private String lastName;
	@Column(name = "user_password", nullable = false)
	private String password;
	@Column(name = "user_email", nullable = false)
	private String email;
	@Column(name = "user_phoneNumber", nullable = false)
	private Long phoneNumber;
	@Column(name = "verify_status", nullable = false)
	private boolean isVerified;
	private LocalDateTime createdDate;
	@Value("null")
	private LocalDateTime updatedDate;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "user", optional = false)
	@JsonIgnore
	private Cart cart;

	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,targetEntity = Address.class)
	@JoinColumn(name="userId")
	private List<Address> addresses;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,targetEntity = OrderedBooks.class)
	@JoinColumn(name="userId")
	private List<OrderedBooks> orderedBooks;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "user", optional = false)
	@JsonIgnore
	private WishList wishlist;
	
}