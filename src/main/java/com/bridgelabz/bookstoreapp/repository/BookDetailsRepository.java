package com.bridgelabz.bookstoreapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstoreapp.model.BookDetails;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Integer> {

	Optional<BookDetails> findByBookId(int bookId);

	Optional<BookDetails> findByBookName(String bookName);
}
