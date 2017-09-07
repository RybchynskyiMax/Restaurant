package com.epam.lab.Servlets.Order;

import com.epam.lab.Dao.OrderDao;
import com.epam.lab.Dao.implementation.OrderDaoImplementation;
import com.epam.lab.Entities.Order;
import com.mongodb.MongoClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrdersClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
        OrderDao orderDao = new OrderDaoImplementation(mongo);
        List<Order> confirmedOrders = orderDao.confirmedOrders();
        List<Order> unConfirmedOrders = orderDao.unConfirmedOrders();
        request.setAttribute("confirmedOrders", confirmedOrders);
        request.setAttribute("notConfirmedOrders", unConfirmedOrders);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/views/OrdersClient.jsp");
        rd.forward(request, response);
    }
}
