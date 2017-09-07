package com.epam.lab.Servlets.Order;

import com.epam.lab.Dao.DishDao;
import com.epam.lab.Dao.OrderDao;
import com.epam.lab.Dao.implementation.DishDaoImplementation;
import com.epam.lab.Dao.implementation.OrderDaoImplementation;
import com.epam.lab.Entities.Dish;
import com.epam.lab.Entities.Order;
import com.mongodb.MongoClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }
        System.out.println("Order edit requested with id=" + id);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        OrderDao orderDao = new OrderDaoImplementation(mongo);
        Order order = new Order();
        order.setId(id);
        order = orderDao.readOrder(order);
        request.setAttribute("order" , order);
        DishDao dishDao = new DishDaoImplementation(mongo);
        List<Dish> dishes = dishDao.readAllDishes();
        request.setAttribute("dishes", dishes);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/views/UpdateOrder.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }

        String[] idDishes = request.getParameterValues("dishes");
        if (idDishes==null) {
            request.setAttribute("error", "Ваше замовлення не може бути пустим");
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            OrderDao orderDao = new OrderDaoImplementation(mongo);
            List<Order> confirmedOrders = orderDao.confirmedOrders();
            List<Order> unConfirmedOrders = orderDao.unConfirmedOrders();
            request.setAttribute("confirmedOrders", confirmedOrders);
            request.setAttribute("notConfirmedOrders", unConfirmedOrders);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/views/OrdersClient.jsp");
            rd.forward(request, response);
        } else {
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            OrderDao orderDao = new OrderDaoImplementation(mongo);
            Order order = new Order();
            order.setId(id);
            double money=0;
            ArrayList<Dish> dishes = new ArrayList<Dish>();
            DishDao dishDao = new DishDaoImplementation(mongo);
            for (String idDish : idDishes) {
                dishes.add(dishDao.findById(idDish));
            }
            order.setDishes(dishes);
            for (Dish dish : dishes) {
                money+=dish.getPrice();
            }
            order.setMoney(money);
            order.setConfirmed(false);
            orderDao.updateOrder(order);
            System.out.println("Order edited successfully with id=" + id);
            request.setAttribute("success", "Замовлення відредактовано успішно!");
            List<Order> confirmedOrders = orderDao.confirmedOrders();
            List<Order> unConfirmedOrders = orderDao.unConfirmedOrders();
            request.setAttribute("confirmedOrders", confirmedOrders);
            request.setAttribute("notConfirmedOrders", unConfirmedOrders);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/OrdersClient.jsp");
            rd.forward(request, response);
        }
    }
}
