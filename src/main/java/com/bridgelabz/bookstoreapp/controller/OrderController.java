package com.bridgelabz.bookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.OrderData;
import com.bridgelabz.bookstoreapp.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	IOrderService orderService;

	@PostMapping("/placeOrder")
	public ResponseEntity<ResponseDTO> placeOrder(@RequestHeader(name = "token") String token,
			@RequestBody OrderDTO orderDto, @RequestParam int bookId) {
		OrderData order = orderService.placeOrder(token, bookId, orderDto);
		ResponseDTO response = new ResponseDTO("Order Placed Successfully  ", "order Details" + order);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/cancelOrder/{orderId}")
	public ResponseEntity<ResponseDTO> cancelOrder(@RequestHeader(name = "token") String token,
			@PathVariable int orderId) {
		String order = orderService.cancelOrder(token, orderId);
		ResponseDTO response = new ResponseDTO("id " + orderId, order);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/userOrders")
	public ResponseEntity<ResponseDTO> getUserOrders(@RequestHeader(name = "token") String token) {
		List<OrderData> userOrders = orderService.userOrders(token);
		ResponseDTO response = new ResponseDTO("Orders of user user", userOrders);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/allOrders")
	public ResponseEntity<ResponseDTO> getAllOrders() {
		List<OrderData> orders = orderService.allOrders();
		ResponseDTO response = new ResponseDTO("Orders of user user", orders);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
