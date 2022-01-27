package com.bridgelabz.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstoreapp.model.OrderData;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

public interface OrderRepository extends JpaRepository<OrderData, Integer> {

//	@Query(value = "SELECT * FROM Orderdetails WHERE user_fk_id= :id, nativeQuery = true")
//	List<OrderData> findAllByUserId(int id);

}
