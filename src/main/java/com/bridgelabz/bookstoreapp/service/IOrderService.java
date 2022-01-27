package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.model.OrderData;

public interface IOrderService {

	OrderData placeOrder(String token, int bookId, OrderDTO orderDto);

	String cancelOrder(String token, int orderId);

	List<OrderData> userOrders(String token);

	List<OrderData> allOrders();
}
