package com.example.demo.service;

import com.example.demo.dto.ItemsOrderRequestDTO;
import com.example.demo.entities.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getOrderByUser(int userId) throws Exception;

    public void createOrder(ItemsOrderRequestDTO itemOrderRequestDTO) throws Exception;

    public Order updateOrder(Order order) throws Exception;

    public Order getOrderByUserLastest(int userId) throws Exception;
}
