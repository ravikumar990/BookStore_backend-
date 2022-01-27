package com.bridgelabz.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstoreapp.model.CartData;

public interface ICartRepository extends JpaRepository<CartData, Integer> {
	@Query(value = "SELECT * FROM cart where user_fk_id = :id", nativeQuery = true)
	List<CartData> findAllCartsByUserId(int id);
}
