package com.bridgelabz.bookstoreapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.dto.ResetPasswordDTO;
import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.exception.UserRegistrationException;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.utl.Email;
import com.bridgelabz.bookstoreapp.utl.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRegistrationService implements IUserRegistrationService {
	String token = null;
	@Autowired
	UserRegistrationRepository userRepo;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private Email email;

	@Autowired
	private MailService mailService;

	@Override
	public List<UserRegistrationData> getUserDeatils() {
		return userRepo.findAll();
	}

	@Override
	public UserRegistrationData userRegistration(UserRegistrationDTO userDTO) {

		Optional<UserRegistrationData> userCheck = userRepo.findByEmailId(userDTO.getEmailId());
		if (userCheck.isPresent()) {
			log.error("Email already exists");
			throw new UserRegistrationException("email already exists");
		} else {
			UserRegistrationData userData = new UserRegistrationData();
//			String pwd = userData.getPassword();
//			String encryptpwd = passwordEncoder.encode(pwd);
//			userData.setPassword(encryptpwd);
			userData.createUser(userDTO);
			userRepo.save(userData);
			email.setTo(userData.getEmailId());
			email.setFrom("ravikumar.telugu990@gmail.com");
			email.setSubject(" User Verification...");
			token = tokenUtil.createToken(userData.getUserId());
			email.setBody(mailService.getLink("http://localhost:8080/userregistration/verify/" + token));
			mailService.send(email.getTo(), email.getSubject(), email.getBody());
			return userData;
		}
	}

	@Override
	public UserRegistrationData getUserById(int userId) {
		return userRepo.findById(userId).orElseThrow(
				() -> new UserRegistrationException("User  with id " + userId + " does not exist in database..!"));
	}

	@Override
	public UserRegistrationData updateUserRegistrationData(int userId, UserRegistrationDTO userDTO) {
		UserRegistrationData userData = this.getUserById(userId);
		userData.updateUser(userDTO);
		return userRepo.save(userData);
	}

	@Override
	public void deleteUser(int userId) {
		UserRegistrationData userData = this.getUserById(userId);
		userRepo.delete(userData);

	}

	@Override
	public String verifyUser(String token) {

		int id = Math.toIntExact(tokenUtil.decodeToken(token));
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);

		if (isPresent.isPresent()) {
			return isPresent.toString();
		} else
			return null;
	}

	@Override
	public Optional<UserRegistrationData> UserLogin(LoginDTO logindto) {

		Optional<UserRegistrationData> userLogin = userRepo.findByEmailIdAndPassword(logindto.getEmailId(),
				logindto.getPassword());

		if (userLogin.isPresent()) {
			log.info("user logged in successfully");
			return userLogin;
		} else {
			log.error("User not Found Exception:");

			return null;
		}
	}

	@Override
	public String forgotPassword(ForgotPasswordDTO forgotPassword) {
		String emailId = forgotPassword.getEmailId();
		Optional<UserRegistrationData> isPresent = userRepo.findByEmailId(emailId);
		if (isPresent.isPresent()) {
			email.setTo(forgotPassword.getEmailId());
			email.setFrom("ravikumar.telugu990@gmail.com");
			email.setSubject("Reset Password Link");
			String token = tokenUtil.createToken(isPresent.get().getUserId());
			email.setBody(mailService.getLink("http://localhost:8080/userregistration/resetpassword/" + token));
			mailService.send(email.getTo(), email.getSubject(), email.getBody());
			return "successfull";
		}
		throw new UserRegistrationException("Email id not found");
	}

	@Override
	public UserRegistrationData resetPassword(ResetPasswordDTO resetpassword, String token) {
		int id = Math.toIntExact(tokenUtil.decodeToken(token));

		Optional<UserRegistrationData> userDetails = userRepo.findById(id);
		if (resetpassword.getNewPassword().equals(resetpassword.getConfirmPassword())) {
			if (userDetails.isPresent()) {
				userDetails.get().setPassword(resetpassword.getNewPassword());
				userDetails.get().setUpdatedDate(LocalDate.now());
				return userRepo.save(userDetails.get());
			}
		}
		return null;
	}


    @Override
    public String deleteAll() {
        userRepo.deleteAll();
        return "Successfully deleted all the users";
    }
	
}
