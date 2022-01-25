package com.bridgelabz.bookstoreapp.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "userregistration")
@Data @ToString
@NoArgsConstructor @AllArgsConstructor
public class UserRegistrationData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column
	private String kyc;

	@Column(name = "emailId")
	private String emailId;

	@Column
	private String password;

	@Column
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dob;

	@Column(name = "registerDate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate registerDate;

	@Column(name = "updatedDate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate updatedDate;

	@Column
	private int otp;

	@Column(name = "purchaseDate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date purchaseDate;

	@Column(name = "expiryDate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date expiryDate;

	public void createUser(UserRegistrationDTO userDTO) {
		this.firstName = userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.kyc = userDTO.getKyc();
		this.emailId = userDTO.getEmailId();
		this.password = userDTO.getPassword();
		this.dob = userDTO.getDob();
		this.registerDate = LocalDate.now();
		this.otp = userDTO.getOtp();
		this.purchaseDate = userDTO.getPurchaseDate();
		this.expiryDate = userDTO.getExpiryDate();

	}

	public void updateUser(UserRegistrationDTO userDTO) {
		this.firstName = userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.kyc = userDTO.getKyc();
		this.emailId = userDTO.getEmailId();
		this.password = userDTO.getPassword();
		this.dob = userDTO.getDob();
		this.registerDate = LocalDate.now();
		this.otp = userDTO.getOtp();
		this.purchaseDate = userDTO.getPurchaseDate();
		this.expiryDate = userDTO.getExpiryDate();

	}

}
