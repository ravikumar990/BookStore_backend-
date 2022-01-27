package com.bridgelabz.bookstoreapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cart")
public class CartData implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	@JoinColumn(name = "user_fk_id", referencedColumnName = "userId")
	private UserRegistrationData user;

	@ManyToOne
	@JoinColumn(name = "book_fk_id", referencedColumnName = "bookId")
	private BookDetails book;

	private int quantity;

	public CartData(UserRegistrationData user, BookDetails book, int quantity) {
		this.user = user;
		this.book = book;
		this.quantity = quantity;
	}
}
