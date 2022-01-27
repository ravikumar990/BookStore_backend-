package com.bridgelabz.bookstoreapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.model.BookDetails;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import com.bridgelabz.bookstoreapp.repository.ICartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.utl.TokenUtil;

@Service
public class CartService implements ICartService {
	@Autowired
	UserRegistrationService userService;

	@Autowired
	BookDetailsService bookService;

	@Autowired
	ICartRepository cartRepo;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	UserRegistrationRepository userRepo;

	@Override
	public CartData addToCart(String token, int bookId, CartDTO cartDTO) {
		int id = Math.toIntExact(tokenUtil.decodeToken(token));
		Optional<UserRegistrationData> user = Optional.ofNullable(userService.getUserById(id));
		if (user.isPresent()) {
			BookDetails book = bookService.getBookById(token, bookId);
			CartData cart = new CartData(user.get(), book, cartDTO.getQuantity());
			return cartRepo.save(cart);
		}
		return null;
	}

	@Override
	public void deleteFromCart(int cartId) {
		cartRepo.deleteById(cartId);
	}

	@Override
	public CartData updateQuantity(String token, int cartId, int quantity) {
		int id = Math.toIntExact(tokenUtil.decodeToken(token));
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			CartData cart = cartRepo.getById(cartId);
			cart.setQuantity(quantity);
			return cartRepo.save(cart);
		}
		return null;
	}

	@Override
	public List<CartData> findAllInCart(String token) {
		int id = Math.toIntExact(tokenUtil.decodeToken(token));
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			List<CartData> cartItems = cartRepo.findAllCartsByUserId(id);
			return cartItems;
		}
		return null;
	}

}
