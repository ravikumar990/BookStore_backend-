package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.model.CartData;

public interface ICartService {

	CartData addToCart(String token, int bookId, CartDTO cartDTO);

	void deleteFromCart(int cartId);

	CartData updateQuantity(String token, int cartId, int quantity);

	List<CartData> findAllInCart(String token);
}
