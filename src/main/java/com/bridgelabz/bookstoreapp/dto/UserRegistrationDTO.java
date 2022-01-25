package com.bridgelabz.bookstoreapp.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @ToString @NoArgsConstructor @AllArgsConstructor
public class UserRegistrationDTO {
	//@Pattern(regexp = "^[A-Z]{1,}[a-zA-z\\s.]{2,}$", message = "FirstName is invalid")
	@NotEmpty(message = "Name can not be NULL")
	private String firstName;

	//@Pattern(regexp = "^[A-Z]{1,}[a-zA-z\\s.]{2,}$", message = "LastName is invalid")
	@NotEmpty(message = "Name can not be NULL")
	private String lastName;

	private String kyc;
	private String emailId;
	private String password;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dob;
	private int otp;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date purchaseDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date expiryDate;
}
