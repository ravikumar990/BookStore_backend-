package com.bridgelabz.bookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.repository.ICartRepository;
import com.bridgelabz.bookstoreapp.service.ICartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private ICartService cartService;

	@Autowired
	ICartRepository cartRepo;

	@PostMapping("/add")
	ResponseEntity<ResponseDTO> addToCart(@RequestHeader(name = "token") String token, @RequestParam int bookId,
			@RequestBody CartDTO cartDTO) {
		CartData add = cartService.addToCart(token, bookId, cartDTO);
		ResponseDTO response = new ResponseDTO("Product Added To Cart ", add);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/remove/{cartId}")
	ResponseEntity<ResponseDTO> removeFromCart(@PathVariable("cartId") int cartId) {
		cartService.deleteFromCart(cartId);
		ResponseDTO response = new ResponseDTO("Delete call success for item Removed From Cart ",
				"deleted id:" + cartId);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@PutMapping("/update/{cartId}/{quantity}")
	ResponseEntity<ResponseDTO> updateCart(@RequestHeader(name = "token") String token,
			@PathVariable("cartId") int cartId, @PathVariable("quantity") int quantity) {
		CartData cart = cartService.updateQuantity(token, cartId, quantity);
		ResponseDTO response = new ResponseDTO("Update call success for item Quantity updated From Cart ", cart);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@GetMapping("/get")
	ResponseEntity<ResponseDTO> findAllCartsByUser(@RequestHeader(name = "token") String token) {
		List<CartData> allItemsForUser = cartService.findAllInCart(token);
		ResponseDTO response = new ResponseDTO("All Items in Cart for user ", allItemsForUser);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	ResponseEntity<ResponseDTO> findAllCarts() {
		List<CartData> allItems = cartRepo.findAll();
		ResponseDTO response = new ResponseDTO("All Items in Cart ", allItems);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

}
