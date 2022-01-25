package com.bridgelabz.bookstoreapp.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.bookstoreapp.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.dto.ResetPasswordDTO;
import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;

public interface IUserRegistrationService {

	List<UserRegistrationData> getUserDeatils();

	UserRegistrationData userRegistration(UserRegistrationDTO userDTO);

	UserRegistrationData getUserById(int userId);

	UserRegistrationData updateUserRegistrationData(int userId, UserRegistrationDTO userDTO);

	void deleteUser(int userId);

	String verifyUser(String token);

	Optional<UserRegistrationData> UserLogin(LoginDTO logindto);

	String forgotPassword(ForgotPasswordDTO forgotpass);

	UserRegistrationData resetPassword(ResetPasswordDTO resetPassword, String token);
	
    String deleteAll();

}
