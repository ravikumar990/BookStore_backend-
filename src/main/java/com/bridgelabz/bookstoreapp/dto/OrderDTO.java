package com.bridgelabz.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private int price;
	private int quantity;
	private String address;
	//private int userId;
	//private int bookId;
	private boolean cancel = false;
}
