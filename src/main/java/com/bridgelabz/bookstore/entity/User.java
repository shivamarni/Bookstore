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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name="user")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,scope = User.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(name = "User_Name", nullable = false)
	private String name;
	@Column(name = "password", nullable = false)
	
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
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,targetEntity = Order.class)
	@JoinColumn(name="userId")
	private List<Order> orderedBooks;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "user", optional = false)
	@JsonIgnore
	private WishList wishlist;
	
}