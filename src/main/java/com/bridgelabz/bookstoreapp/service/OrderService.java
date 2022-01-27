package com.bridgelabz.bookstoreapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.exception.UserRegistrationException;
import com.bridgelabz.bookstoreapp.model.BookDetails;
import com.bridgelabz.bookstoreapp.model.OrderData;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import com.bridgelabz.bookstoreapp.repository.BookDetailsRepository;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.utl.TokenUtil;

@Service
public class OrderService implements IOrderService {
	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	UserRegistrationRepository userRepo;

	@Autowired
	BookDetailsRepository bookRepo;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public OrderData placeOrder(String token, int bookId, OrderDTO orderDto) {
		OrderData order = new OrderData();
		int id = Math.toIntExact(tokenUtil.decodeToken(token));
		Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
		Optional<BookDetails> isBookPresent = bookRepo.findById(bookId);
		if (isUserPresent.isPresent() && isBookPresent.isPresent()) {
			order.setUserRegistrationData(isUserPresent.get());
			order.setBookDetails(isBookPresent.get());
			order.crateOrder(orderDto);
		} else {
			throw new UserRegistrationException("userid or bookid is invalid");
		}
		return orderRepo.save(order);
	}

	@Override
	public String cancelOrder(String token, int orderId) {
		int id = Math.toIntExact(tokenUtil.decodeToken(token));
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			Optional<OrderData> order = orderRepo.findById(orderId);
			if (order.isPresent()) {
				order.get().setCancel(true);
				orderRepo.save(order.get());
				return "Cancel order Successfull";
			}

		}
		return "cancel order not successfull";
	}

	@Override
	public List<OrderData> userOrders(String token) {
//		int userId = Math.toIntExact(tokenUtil.decodeToken(token));
//		Optional<UserRegistrationData> isPresent = userRepo.findById(userId);
//		if (isPresent.isPresent()) {
//			List<OrderData> orders = orderRepo.findAllByUserId(userId);
//			return orders;
//		}
		return null;
	}

	@Override
	public List<OrderData> allOrders() {
		List<OrderData> orders = orderRepo.findAll();
		if (orders.isEmpty()) {
			return null;
		} else {
			for (int i = 0; i < orders.size(); i++) {
				int id = orders.get(i).getOrderId();
				Optional<OrderData> orderByOrderId = orderRepo.findById(id);
				if (orderByOrderId.isPresent()) {
					orderByOrderId.get().setCancel(false);
					orderRepo.save(orderByOrderId.get());
					return orders;
				}
			}

		}
		return null;
	}
}
