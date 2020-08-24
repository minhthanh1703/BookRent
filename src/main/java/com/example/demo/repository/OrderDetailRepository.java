package com.example.demo.repository;

import com.example.demo.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> getAllByOrderId(int orderId);

}
