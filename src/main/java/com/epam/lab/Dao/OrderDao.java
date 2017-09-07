package com.epam.lab.Dao;

import com.epam.lab.Entities.Order;
import org.bson.types.ObjectId;

import java.util.List;

public interface OrderDao {
    Order createOrder(Order order);

    void updateOrder(Order order);

    List<Order> readAllOrders();

    void deleteOrder(Order order);

    Order readOrder(Order order);

    List<Order> confirmedOrders();

    List<Order> unConfirmedOrders();

    List<Order> findOrdersByDishId(String id);
}
