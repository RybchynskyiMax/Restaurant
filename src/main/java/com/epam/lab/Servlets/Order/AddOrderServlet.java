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

public class AddOrderServlet extends HttpServlet {
    private static final long serialVersionUID = -7060758261496829905L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] idDishes = request.getParameterValues("dishes");
        if (idDishes==null) {
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            DishDao dishDao = new DishDaoImplementation(mongo);
            request.setAttribute("error", "Ваше замовлення не може бути пустим");
            List<Dish> allDishes = dishDao.readAllDishes();
            request.setAttribute("dishes", allDishes);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/views/MenuClient.jsp");
            rd.forward(request, response);
        } else {
            Order order = new Order();
            double money=0;
            ArrayList<Dish> dishes = new ArrayList<Dish>();
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            DishDao dishDao = new DishDaoImplementation(mongo);
            OrderDao orderDao = new OrderDaoImplementation(mongo);
            for (String idDish : idDishes) {
                dishes.add(dishDao.findById(idDish));
            }
            order.setDishes(dishes);
            for (Dish dish : dishes) {
                money+=dish.getPrice();
            }
            order.setMoney(money);
            order.setConfirmed(false);
            orderDao.createOrder(order);
            System.out.println("Order Added Successfully with id="+order.getId());
            request.setAttribute("success", "Замовлення додано успішно");
            List<Dish> allDishes = dishDao.readAllDishes();
            request.setAttribute("dishes", allDishes);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/views/MenuClient.jsp");
            rd.forward(request, response);
        }
    }
}
