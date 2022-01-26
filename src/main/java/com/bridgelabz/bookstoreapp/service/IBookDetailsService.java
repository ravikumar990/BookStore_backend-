package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import com.bridgelabz.bookstoreapp.dto.BookDetailsDTO;
import com.bridgelabz.bookstoreapp.model.BookDetails;

public interface IBookDetailsService {

	List<BookDetails> showAllBooks(String token);

	BookDetails addBook(String token, BookDetailsDTO bookDto);

	BookDetails getBookById(String token, int bookId);

	BookDetails getBookByName(String token, String bookName);

	BookDetails updateBook(String token, int bookId, BookDetailsDTO bookDTO);

	void deleteBook(String token, int bookId);

	BookDetails updateBookQuantity(String token, int bookId, int bookQuantity);

	BookDetails updateBookPrice(String token, int bookId, int bookPrice);
}
