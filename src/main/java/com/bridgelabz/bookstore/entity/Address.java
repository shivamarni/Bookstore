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
@Data
@Table(name = "address")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,scope = Address.class)
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	private String name;
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
