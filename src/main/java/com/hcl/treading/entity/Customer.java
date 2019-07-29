package com.hcl.treading.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="customer")
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerId;	
	
	@Column(name="full_name")	
	private String fullName;
	
	@JsonIgnore
	@Column(name="birth_date")	
	private LocalDate birthDate;
	
	@JsonIgnore
	@Column(name="gender")	
	private String gender;
	
	@JsonIgnore
	@Column(name="mobile_no")	
	private String mobileNo;
	
	@JsonIgnore
	@Column(name="email")	
	private String email;
	
	@JsonIgnore
	@Column(name="address")	
	private String address;
	
}
