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

public class ConfirmedOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }

            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            OrderDao orderDao = new OrderDaoImplementation(mongo);
            Order order = new Order();
            order.setId(id);
            order = orderDao.readOrder(order);
            order.setConfirmed(true);
            orderDao.updateOrder(order);
            System.out.println("Order confirmed successfully with id=" + id);
            request.setAttribute("success", "Замовлення підтверджено успішно");
        List<Order> confirmedOrders = orderDao.confirmedOrders();
        List<Order> unConfirmedOrders = orderDao.unConfirmedOrders();
        request.setAttribute("confirmedOrders", confirmedOrders);
        request.setAttribute("notConfirmedOrders", unConfirmedOrders);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/OrdersAdmin.jsp");
            rd.forward(request, response);
        }
    }

